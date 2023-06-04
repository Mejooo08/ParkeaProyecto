package co.edu.unbosque.parkea.captcha;

public class CaptchaSettings {
    private String captcha;
    private String hiddenCaptcha;
    private String realCaptcha;

    /**
     * Este método se usa para traer el captcha
     * @return
     */
    public String getCaptcha() {
        return captcha;
    }

    /**
     * Este método se usa para darle un valor al captcha
     * @param captcha
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    /**
     * Este método se usa para traer el captcha oculto
     * @return
     */
    public String getHiddenCaptcha() {
        return hiddenCaptcha;
    }

    /**
     * Este método se usa para darle un valor al captcha oculto
     * @param hiddenCaptcha
     */
    public void setHiddenCaptcha(String hiddenCaptcha) {
        this.hiddenCaptcha = hiddenCaptcha;
    }

    /**
     * Este método se usa para obtener el captcha real
     * @return
     */
    public String getRealCaptcha() {
        return realCaptcha;
    }

    /**
     * Este método se usa para darle un valor al captcha real
     * @param realCaptcha
     */
    public void setRealCaptcha(String realCaptcha) {
        this.realCaptcha = realCaptcha;
    }
}
