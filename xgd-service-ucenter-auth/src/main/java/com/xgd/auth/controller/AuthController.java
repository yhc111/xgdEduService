package com.xgd.auth.controller;

import com.xgd.api.auth.AuthControllerApi;
import com.xgd.auth.service.AuthService;
import com.xgd.framework.domain.ucenter.ext.AuthToken;
import com.xgd.framework.domain.ucenter.request.LoginRequest;
import com.xgd.framework.domain.ucenter.response.AuthCode;
import com.xgd.framework.domain.ucenter.response.JwtResult;
import com.xgd.framework.domain.ucenter.response.LoginResult;
import com.xgd.framework.exception.ExceptionCast;
import com.xgd.framework.model.response.CommonCode;
import com.xgd.framework.model.response.ResponseResult;
import com.xgd.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/")
public class AuthController implements AuthControllerApi {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    AuthService authService;

    @Override
    @PostMapping("/userlogin")
    public LoginResult login(LoginRequest loginRequest) {
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        //??????
        String username = loginRequest.getUsername();
        //??????
        String password = loginRequest.getPassword();

        //????????????
        AuthToken authToken =  authService.login(username,password,clientId,clientSecret);

        //??????????????????
        String access_token = authToken.getAccess_token();
        //??????????????????cookie
        this.saveCookie(access_token);

        return new LoginResult(CommonCode.SUCCESS,access_token);
    }

    //??????????????????cookie
    private void saveCookie(String token){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,cookieMaxAge,false);

    }
    //???cookie??????token
    private void clearCookie(String token){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,0,false);

    }

    //??????
    @Override
    @PostMapping("/userlogout")
    public ResponseResult logout() {
        //??????cookie????????????????????????
        String uid = getTokenFormCookie();
        //??????redis??????token
        boolean result = authService.delToken(uid);
        //??????cookie
        this.clearCookie(uid);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    @GetMapping("/userjwt")
    public JwtResult userjwt() {
        //??????cookie????????????????????????
        String uid = getTokenFormCookie();
        if(uid == null){
            return new JwtResult(CommonCode.FAIL,null);
        }
        //??????????????????redis?????????jwt??????
        AuthToken userToken = authService.getUserToken(uid);
        if(userToken!=null){
            //???jwt?????????????????????
            String jwt_token = userToken.getJwt_token();
            return new JwtResult(CommonCode.SUCCESS,jwt_token);
        }
        return null;
    }

    //??????cookie??????????????????
    private String getTokenFormCookie(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if(map!=null && map.get("uid")!=null){
            String uid = map.get("uid");
            return uid;
        }
        return null;
    }
}
