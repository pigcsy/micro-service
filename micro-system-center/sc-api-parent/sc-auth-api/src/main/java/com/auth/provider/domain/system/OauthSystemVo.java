package com.auth.provider.domain.system;

import lombok.Data;

/**
 * Created by maven on 2017/3/13. _oo0oo_ o8888888o 88" . "88 (| -_- |) 0\ = /0
 * ___/`---'\___ .' \\| |// '. / \\||| : |||// \ / _||||| -:- |||||- \ | | \\\ -
 * /// | | | \_| ''\---/'' |_/ | \ .-\__ '-' ___/-. / ___'. .' /--.--\ `. .'___
 * ."" '< `.___\_<|>_/___.' >' "". | | : `- \`.;`\ _ /`;.`/ - ` : | | \ \ `_. \_
 * __\ /__ _/ .-` / / =====`-.____`.___ \_____/___.-`___.-'===== `=---='
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 佛祖开光 永无BUG
 */
@Data
public class OauthSystemVo {
	private Integer systemId;
	private String systemName;
	private String clientId;
	private Integer systemType;
	private Integer dailyAccessNum;
	private Integer minuteAccessNum;
	
}
