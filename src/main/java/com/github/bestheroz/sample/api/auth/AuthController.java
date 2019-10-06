package com.github.bestheroz.sample.api.auth;

import com.github.bestheroz.standard.common.constant.CommonCode;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.protocol.CommonResponseVO;
import com.github.bestheroz.standard.common.util.MyResponseUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "권한")
@RestController
@RequestMapping(value = "/sample/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private AuthService authService;

    @ApiOperation(value = "로그인 시도")
    @ApiResponses({@ApiResponse(code = 200, message = CommonCode.SWAGGER_COMMON_200_MESSAGE)})
    @PostMapping(value = "/login")
    public CommonResponseVO doLogin(@ApiParam("회원 아이디") @RequestParam(value = "memberId") final String memberId,
                                    @ApiParam("회원 비밀번호(SHA512 값)") @RequestParam(value = "memberPw") final String memberPw) throws CommonException {
        this.authService.login(memberId, memberPw);
        return MyResponseUtils.SUCCESS_NORMAL;
    }

    @ApiOperation(value = "토큰 검증")
    @ApiResponses({@ApiResponse(code = 200, message = CommonCode.SWAGGER_COMMON_200_MESSAGE)})
    @PostMapping(value = "/verify")
    public CommonResponseVO verify(@ApiParam("토큰") @RequestHeader(value = "x-access-token") final String token) throws CommonException {
        this.logger.debug(token);
        this.authService.verify(token);
        return MyResponseUtils.SUCCESS_NORMAL;
    }
}