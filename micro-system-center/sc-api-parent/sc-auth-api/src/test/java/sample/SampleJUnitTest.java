package sample;

import com.auth.boot.AuthApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ${DESCRIPTION}
 *
 * @author csy
 * @date 2017/2/20 下午3:27
 * <pre>
 *                     _oo0oo_
 *                    o8888888o
 *                    88" . "88
 *                    (| -_- |)
 *                    0\  =  /0
 *                  ___/`---'\___
 *                .' \\|     |// '.
 *               / \\|||  :  |||// \
 *              / _||||| -:- |||||- \
 *             |   | \\\  -  /// |   |
 *             | \_|  ''\---/''  |_/ |
 *             \  .-\__  '-'  ___/-. /
 *           ___'. .'  /--.--\  `. .'___
 *        ."" '<  `.___\_<|>_/___.' >' "".
 *       | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *       \  \ `_.   \_ __\ /__ _/   .-` /  /
 *   =====`-.____`.___ \_____/___.-`___.-'=====
 *                     `=---='
 *   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *             佛祖开光         永无BUG
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class SampleJUnitTest {

    // public static void main(String[] args) {
    //
    //
    // String publicKey=
    // "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrYlYe7NoqO/2X7CbkWUF89Ovyz5RU2lPFU4X64yMvzEODc1ma6TWICvxKIt9BDWz2kaXNmk+4TiYJ1nb93yyxpOyAVYhYMc8Fe15h+Su5RdgLIVCf/G7eJHJA2LdW9O9rmRYWpXpUL3rFrlk65ljnMeL3oBaCQ396UzMbaf9VjwIDAQAB";
    // String privateKey=
    // "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKtiVh7s2io7/ZfsJuRZQXz06/LPlFTaU8VThfrjIy/MQ4NzWZrpNYgK/Eoi30ENbPaRpc2aT7hOJgnWdv3fLLGk7IBViFgxzwV7XmH5K7lF2AshUJ/8bt4kckDYt1b072uZFhalelQvesWuWTrmWOcx4vegFoJDf3pTMxtp/1WPAgMBAAECgYA+Ili2vYga58T431T6VKG5e9JPJZLRcJYOZEmeu9p16chQT/GciBAwUktFVX87RnLKseM6t0dPuoVv66EYSWEaLHJc81sOMT7ahAr96zL6sQJARGAqsBvO9FW8oSBOQLDY6UTWx+yVl7YWxJ0qsw76NGTfmoc3/1MIo/pMn8TUAQJBAPgMCY0U1TzCYXwTztKpW2toE53K0OWF+LGEkDyGsMLpNUyi+5HYFIQN6usitYOZAXwOEwPEU5zlJBOVWLwxQ28CQQCw4Q1kCJzbDNvSEM2GLSRnfF1/YXHSvtfUGzDLdURwcHk/460gWru+m2qX5ooJsRnohj1l8cnneatVemn2MX/hAkEA7I1uPmFzynIFzfKF91kTmiazLnRRK9LMDV9CvrYktHz7G48DI19SkW1Eb+G2AT5VsB0r/ReqNaJA+XwgF7lTeQJAV6ymO5B9V6LvDxgfF8QmiFk/6xT9G45XYx67OA5/hxBpOX78YM44w4k78+FlSNqNUhHAO1j3UJKhl7Qe89ajYQJANAOkPb9m9LMznuY5V94RQNoyW64fjLEMzYMWlPF2blcNVoVcs+NW3g+M7ZcpaPK1qsQWnJXT8ZKfbEsvcVr4HQ==";
    //
    // String a =
    // "fmV9ZT395YcpBIu6mVc27teMkMvnZaV6ES1DVh0+LARjrPw6W5/PkUUWnAL45J1i3XxipSJtDeiysBZvFw6FvSpnq1qzT9TbLzPKoOMqBeasha7cPeXp3h/FU8VO5fdvoha88QfXm855HMKmMj/I6xRCamQprouCzt6Qch6tvCc=";
    //
    // String b =
    // "fmV9ZT395YcpBIu6mVc27teMkMvnZaV6ES1DVh0%2BLARjrPw6W5%2FPkUUWnAL45J1i3XxipSJtDeiysBZvFw6FvSpnq1qzT9TbLzPKoOMqBeasha7cPeXp3h%2FFU8VO5fdvoha88QfXm855HMKmMj%2FI6xRCamQprouCzt6Qch6tvCc%3D";
    // try {
    // // a = RSAUtils.encryptByPublicKey("123456", publicKey);
    // String pwd1 = URLEncoder.encode(a);
    // System.out.println(pwd1);
    // System.out.println(pwd1.equalsIgnoreCase(b));
    // pwd1 = URLDecoder.decode(pwd1);
    // b = URLDecoder.decode(b);
    //
    // System.out.println(RSAUtils.decryptByPrivateKey(b,privateKey));
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    //
    // }
}
