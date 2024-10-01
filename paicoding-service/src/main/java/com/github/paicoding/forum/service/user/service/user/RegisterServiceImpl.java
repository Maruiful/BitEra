package com.github.paicoding.forum.service.user.service.user;

import com.github.paicoding.forum.api.model.enums.NotifyTypeEnum;
import com.github.paicoding.forum.api.model.enums.user.LoginTypeEnum;
import com.github.paicoding.forum.api.model.exception.ExceptionUtil;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.api.model.vo.notify.NotifyMsgEvent;
import com.github.paicoding.forum.api.model.vo.user.UserPwdLoginReq;
import com.github.paicoding.forum.core.util.RandUtil;
import com.github.paicoding.forum.core.util.SpringUtil;
import com.github.paicoding.forum.core.util.StarNumberUtil;
import com.github.paicoding.forum.core.util.TransactionUtil;
import com.github.paicoding.forum.service.user.converter.UserAiConverter;       
import com.github.paicoding.forum.service.user.repository.dao.UserAiDao;        
import com.github.paicoding.forum.service.user.repository.dao.UserDao;
import com.github.paicoding.forum.service.user.repository.entity.UserAiDO;      
import com.github.paicoding.forum.service.user.repository.entity.UserDO;        
import com.github.paicoding.forum.service.user.repository.entity.UserInfoDO;    
import com.github.paicoding.forum.service.user.service.RegisterService;
import com.github.paicoding.forum.service.user.service.help.UserPwdEncoder;     
import com.github.paicoding.forum.service.user.service.help.UserRandomGenHelper;
import org.springframework.stereotype.Service;

/**
 * 用户注册服务
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Override
    public Long registerSystemUser(String loginUser, String nickUser, String avatar) { return null; }                                                           

    @Override
    public Long registerByUserNameAndPassword(UserPwdLoginReq loginReq) { return null; }                                                                        

    @Override
    public Long registerByWechat(String thirdAccount) { return null; }

    
    public void run() {}
}