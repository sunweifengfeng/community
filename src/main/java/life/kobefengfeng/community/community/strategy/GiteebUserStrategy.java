package life.kobefengfeng.community.community.strategy;

import life.kobefengfeng.community.community.dto.AccessTokenDTO;
import life.kobefengfeng.community.community.provider.GiteeProvider;
import life.kobefengfeng.community.community.provider.dto.GiteeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/7/8 18:57
 * @Version 1.0
 */
@Service
public class GiteebUserStrategy implements UserStrategy{
    @Autowired
    private GiteeProvider giteeProvider;

    @Override
    public LoginUserInfo getUser(String code, String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        GiteeUser giteeUser = giteeProvider.getUser(accessToken);
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setAvatar_url(giteeUser.getAvatar_url());
        loginUserInfo.setBio(giteeUser.getBio());
        loginUserInfo.setId(giteeUser.getId());
        loginUserInfo.setName(giteeUser.getName());
        return loginUserInfo;
    }

    @Override
    public String getSupportedType() {
        return "gitee";
    }
}
