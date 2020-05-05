package com.memory.service;

import com.memory.dao.MsgDAO;
import com.memory.dao.UserDAO;
import com.memory.pojo.Msg;
import com.memory.pojo.User;
import com.memory.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("msgServiceImpl")
public class MsgServiceImpl implements MsgService {
    @Autowired
    private MsgDAO msgDao;

    @Autowired
    private UserDAO userDao;

    /**
     * 将一个netty里面的Msg对象转换为Msg并在dao内部转换为
     * pojo的Msg并存入,之后返回存入的Dao的msgID
     * @param msg 由前端传过来的Msg模型
     * @return 保存之后的msgId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer save(com.memory.netty.Msg msg) {
        Integer senderId = msg.getSenderId();
        Integer receiverId = msg.getReceiverId();
        User senderUser = userDao.get(senderId);
        User receiverUser = userDao.get(receiverId);
        Msg msg1 = new Msg();
        msg1.setSendUser(senderUser);
        msg1.setReceiveUser(receiverUser);
        msg1.setMsgAction(0);
        msg1.setMsgDatetime(new Date());
        msg1.setMsgContent(msg.getContent());

        // 返回保存之后的msgId
        return msgDao.save(msg1);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMsgSigned(List<Integer> msgIdList) {
        // 先这么做吧
        // 感觉有点慢,是不是要用自定义sql
        System.out.println(msgIdList.toString());
        for (Integer msgId : msgIdList) {
            Msg msg = msgDao.get(msgId);
            msg.setMsgAction(1);
            msgDao.save(msg);
        }
        Object msgServiceImpl = SpringUtils.getBean("msgServiceImpl");
        System.out.println(msgServiceImpl);
    }
    // 获取未签收消息的id
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<com.memory.netty.Msg> getUnReadMsgList(Integer acceptUserId) {
        System.out.println("acceptUserId is :" + acceptUserId);
        List<Msg> unReadMsgList = msgDao.getUnReadMsgList(acceptUserId);
        System.out.println(unReadMsgList.toString());
        List<com.memory.netty.Msg> unRead = new ArrayList<>();
        if (unReadMsgList==null) {
            return null;
        }
        for (Msg msg : unReadMsgList) {
            com.memory.netty.Msg temp = new com.memory.netty.Msg();
            temp.setSenderId(msg.getSendUser().getUserId());
            temp.setReceiverId(msg.getReceiveUser().getUserId());
            System.out.println("聊天消息为"+msg.getMsgContent());
            temp.setContent(msg.getMsgContent());
            temp.setMsgId(msg.getMsgId());
            unRead.add(temp);
            System.out.println(temp);
        }
        return unRead;
    }
}
