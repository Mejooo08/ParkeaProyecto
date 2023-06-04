package co.edu.unbosque.parkea.controllers;

import cn.apiclub.captcha.Captcha;
import co.edu.unbosque.parkea.captcha.CaptchaGenerator;
import co.edu.unbosque.parkea.captcha.CaptchaSettings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pagina_principal")
public class InicioController {
    /**
     * Este método se usa para registrar un usuario
     * @param model
     * @return
     */
    @GetMapping("/inicio_principal")
    public String register(Model model) {
        model.addAttribute("captcha", genCaptcha());
        return "inicio_principal";
    }

    /**
     * Este método se usa para verificar los datos del registro
     * @param captchaSettings
     * @param model
     * @return
     */
    @PostMapping("/verify")
    public String verify(@ModelAttribute CaptchaSettings captchaSettings, Model model) {
        if(captchaSettings.getCaptcha().equals(captchaSettings.getHiddenCaptcha())){
            model.addAttribute("message","Captcha verified successfully");
            return "success";
        }
        else {
            model.addAttribute("message","Invalid Captcha");
            model.addAttribute("captcha",genCaptcha());
        }
        return "inicio_principal";
    }

    /**
     * Este método se usa para generar el captcha
     * @return
     */
    private CaptchaSettings genCaptcha() {
        CaptchaSettings captchaSettings = new CaptchaSettings();
        Captcha captcha = CaptchaGenerator.generateCaptcha(260, 80);
        captchaSettings.setHiddenCaptcha(captcha.getAnswer());
        captchaSettings.setCaptcha("");
        captchaSettings.setRealCaptcha(CaptchaGenerator.encodeCaptchatoBinary(captcha));
        return captchaSettings;
    }

}
