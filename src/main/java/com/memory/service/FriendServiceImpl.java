package com.memory.service;

import com.memory.controller.VO.*;
import com.memory.dao.FriendDAO;
import com.memory.dao.UserDAO;
import com.memory.pojo.Friend;
import com.memory.pojo.User;
import com.memory.pojo.UserTag;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class FriendServiceImpl implements FriendService{
    @Autowired
    private FriendDAO friendDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserTagService userTagService;


    @Resource
    private HibernateTemplate hibernateTemplate;

    private final TrustInfoVO trustInfoVO = new TrustInfoVO() ;
    private final UntrustInfoVO untrustInfoVO = new UntrustInfoVO();


    /**
     * @Description: 获取所有好友列表得实体类信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> queryFriendsList(int userId) {
        String hql1 = "select addedId from Friend f where f.userId= ? ";
        System.out.println(hql1);
        List<Integer> add_id= (List<Integer>) hibernateTemplate.find(hql1,userId);
        System.out.println(add_id);
        return getUsers(add_id);
    }

    private List<User> getUsers(List<Integer> add_id) {
        Session session = Objects.requireNonNull(hibernateTemplate.getSessionFactory()).getCurrentSession();
        String hql3 = "from User as user where user.userId in (:list)";
        if(add_id.isEmpty()){
            return null;
        }
        List<User> users= (List<User>)session.createQuery(hql3).setParameterList("list",add_id).list();
        return users;
    }


    /**
     * @Description: 获取所有好友指定数据格式列表
     */
    @Override
    public List<FriendInfoVO> getFriendsList(int userId) {
        List<FriendInfoVO> friends = new ArrayList<FriendInfoVO>();
        List<User> users = queryFriendsList(userId);
        for (User user: users) {
            FriendInfoVO friendInfoVO = new FriendInfoVO();
            friendInfoVO.setUserId(user.getUserId());
            friendInfoVO.setIcon(user.getIcon());
            Friend friend;
            friend = friendDAO.get(userId,user.getUserId());
            friendInfoVO.setLevel(friend.getLevel());
            friendInfoVO.setNickname(user.getNickname());
            friendInfoVO.setRemark(friend.getRemark());
            friends.add(friendInfoVO);
        }

        return friends;
    }

    public boolean isExitUser(int userId) {
        String hql = "from User u where u.userId=?";
        List<User> users=(List<User>) hibernateTemplate.find(hql,userId);
        return !users.isEmpty();

    }

    @Override
    public void saveFriends(Integer myUserId, Integer friendUserId) {
        Friend friend = new Friend();
        friend.setUserId(myUserId);
        friend.setAddedId(friendUserId);
        friend.setLevel(0);
        friendDAO.add(friend);
    }

    @Override
    public void deleteFriend(Integer myUserId, Integer friendUserId) {
        Friend friend = new Friend();
        friend.setUserId(myUserId);
        friend.setAddedId(friendUserId);
        friend.setLevel(0);
        friendDAO.delete(friend);
    }



    /**
     * 基于本系统的结构从而设计的推荐朋友的算法，每次推荐5人
     * 推荐的符合算法的朋友3名，且并非为朋友或者黑名单成员
     * 除此之外，随机推荐任意非朋友或者黑名单成员2名
     * 两次刷新出现的朋友不能完全一致（可通过随机进行打乱）
     * 算法首先先选取该用户的标签中以往出现次数的前三名，且出现次数至少为3次（不到3次不选取，不足3个标签则空缺）
     * 然后通过前三名的标签与其他的用户随机进行匹配，会通过一个算法得出一个匹配分数
     * 达到一定的分数则匹配成功，成为推荐朋友，满3名则停止匹配
     * 若匹配一段时间后无法匹配成功3人，则空缺由随机推荐补上
     * 匹配算法比较前三名标签的符合程度，有一定的权重
     */
    @Override
    public List<RecommendFriendInfoVO> recommendFriends(Integer myUserId){


        int cnt = userDAO.getCount(); //用户总人数
        List<Integer> randomId = new ArrayList<>();//记录推荐的用户id
        int random;

        int num = 0; //总共匹配成功的人数
        int match = 0; //进行匹配的次数，多于100次或者超过可匹配人数上限时停止匹配（刚开始时可能用户较少）
        User tmp;
        List<User> myFriends = queryFriendsList(myUserId);
        List<User> myBlackList = queryBlackList(myUserId);

        //匹配推荐
        while(num < 3 && match < 50){
             random = getRandomId(cnt);
             tmp = userDAO.get(random);
             if((myFriends != null) && (myBlackList != null)){
                 if((isMatching(myUserId,random)) && (myUserId != random) && (tmp != null) && (!myFriends.contains(tmp)) && (!myBlackList.contains(tmp))){
                    if(!randomId.contains(random)){
                        randomId.add(num,random);
                        num++;
                    }
                 }
             }
             else if((myFriends == null) && (myBlackList != null)){
                 if((isMatching(myUserId,random)) && (myUserId != random) && (tmp != null) && (!myBlackList.contains(tmp))){
                     if(!randomId.contains(random)){
                         randomId.add(num,random);
                         num++;
                     }
                 }
             }

             else if(myFriends != null){
                 if((isMatching(myUserId,random)) && (myUserId != random) && (tmp != null) && (!myFriends.contains(tmp))){
                     if(!randomId.contains(random)){
                         randomId.add(num,random);
                         num++;
                     }
                 }
             }
             else {
                 if((isMatching(myUserId,random)) && (myUserId != random) && (tmp != null)){
                     if(!randomId.contains(random)){
                         randomId.add(num,random);
                         num++;
                     }
                 }
             }


             match++;
        }

        //随机推荐
        while(num < 5){
            random = getRandomId(cnt);
            tmp = userDAO.get(random);
            if((myFriends != null) && (myBlackList != null)){
                if((myUserId != random) && (tmp != null) && (!myFriends.contains(tmp)) && (!myBlackList.contains(tmp))){
                    if(!randomId.contains(random)){
                    randomId.add(num,random);
                    num++;
                    }
                }
            }
            else if((myFriends == null) && (myBlackList != null)){
                if((myUserId != random) && (tmp != null) && (!myBlackList.contains(tmp))){
                    if(!randomId.contains(random)){
                        randomId.add(num,random);
                        num++;
                    }
                }
            }

            else if(myFriends != null){
                if((myUserId != random) && (tmp != null) && (!myFriends.contains(tmp))){
                    if(!randomId.contains(random)){
                        randomId.add(num,random);
                        num++;
                    }
                }
            }
            else {
                if((myUserId != random) && (tmp != null)){
                    if(!randomId.contains(random)){
                        randomId.add(num,random);
                        num++;
                    }
                }
            }

        }
        List<RecommendFriendInfoVO> recommendUsers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            recommendUsers.add(addByUserID(randomId.get(i)));
        }

        return recommendUsers;


    }

    @Override
    public int getRandomId(int cnt){
        Random random = new Random();
        return (random.nextInt(cnt)+1);
    }

    @Override
    public List<User> queryBlackList(int userId) {
        String hql1 = "select addedId from Blacklist b where b.addedId= ? ";
        List<Integer> add_id= (List<Integer>) hibernateTemplate.find(hql1,userId);
        String hql2 = "select userId from Blacklist b where b.userId= ? ";
        add_id.addAll((List<Integer>)hibernateTemplate.find(hql2,userId));
        return getUsers(add_id);
    }

    @Override
    public boolean isExitFriend(int userId,int friendId) {
        String hql = "from Friend f where f.userId = ? and f.addedId = ?";
        List<User> users=(List<User>) hibernateTemplate.find(hql,userId,friendId);
        return !users.isEmpty();
    }

    @Override
    public Friend saveLevel(int userId, int friendId) {
        Friend friend = friendDAO.get(userId,friendId);
        friend.setLevel(friend.getLevel()==1?0:1);
        friendDAO.update(friend);
        return friend;
    }

    @Override
    public Friend addRemark(int userId, int friendId, String remark) {
        Friend friend = friendDAO.get(userId,friendId);
        friend.setRemark(remark);
        friendDAO.update(friend);
        return friend;
    }

    @Override
    public TrustInfoVO getTrustedInfo(int userId, int friendId) {
        Friend friend = friendDAO.get(userId,friendId);
        User friendInfo = userDAO.get(friendId);

        trustInfoVO.setGender(friendInfo.getGender());
        trustInfoVO.setIcon(friendInfo.getIcon());
        trustInfoVO.setNickname(friendInfo.getNickname());
        trustInfoVO.setProfile(friendInfo.getProfile());
        trustInfoVO.setThisWeekTag(friendInfo.getThisWeekTag());
        trustInfoVO.setRemark(friend.getRemark());
        return trustInfoVO;
    }


    @Override
    public UntrustInfoVO getUntrustedInfo(int userId, int friendId) {
        Friend friend = friendDAO.get(userId,friendId);
        User friendInfo = userDAO.get(friendId);
        untrustInfoVO.setIcon(friendInfo.getIcon());
        untrustInfoVO.setNickname(friendInfo.getNickname());
        untrustInfoVO.setProfile(friendInfo.getProfile());
        untrustInfoVO.setRemark(friend.getRemark());
        return untrustInfoVO;
    }

    @Override
    public Object getInformation(int userId, int friendId) {
        Friend friend = friendDAO.get(userId,friendId);
        if(friend.getLevel()==1) {
            return getTrustedInfo(userId,friendId);
        } else {
            return getUntrustedInfo(userId,friendId);
        }
    }

    /**
     * 选取出每个用户的标签出现次数前三名(若没有三个符合要求的则少的空缺)
     */

    @Override
    public TagSortVO TagSort(Integer userId){

        // 对标签出现次数进行排序
        List<UserTag> userTag = userTagService.getMyUserTagList(userId);
        if(userTag == null) return null;
        userTag.sort(new UserTagNumberComparator());
        Collections.reverse(userTag);

        TagSortVO tagSortVOS = new TagSortVO();
        tagSortVOS.setUserID(userId);


         if(userTag.size() == 1) {
            if(userTag.get(0).getTagNumber()>=3)
                tagSortVOS.setFirstTagID(userTag.get(0).getTagId());
        }
        else if(userTag.size() == 2){
            if(userTag.get(0).getTagNumber()>=3)
                tagSortVOS.setFirstTagID(userTag.get(0).getTagId());

            if(userTag.get(1).getTagNumber()>=3)
                tagSortVOS.setSecondTagID(userTag.get(1).getTagId());
        }
        else {
            if(userTag.get(0).getTagNumber()>=3)
            tagSortVOS.setFirstTagID(userTag.get(0).getTagId());

            if(userTag.get(1).getTagNumber()>=3)
                tagSortVOS.setSecondTagID(userTag.get(1).getTagId());

            if(userTag.get(2).getTagNumber()>=3)
                tagSortVOS.setThirdTagID(userTag.get(2).getTagId());}


        return tagSortVOS;
    }

    @Override
    public RecommendFriendInfoVO addByUserID(Integer userID) {
        User u1 = userDAO.get(userID);
        RecommendFriendInfoVO rf1 = new RecommendFriendInfoVO();
        rf1.setUserId(userID);
        rf1.setIcon(u1.getIcon());
        rf1.setNickname(u1.getNickname());
        return rf1;
    }

    @Override
    public boolean isMatching(Integer userId, Integer friendId) {
        int pt = 0;
        TagSortVO tagSortVO1 = TagSort(userId);
        TagSortVO tagSortVO2 = TagSort(friendId);
        if(tagSortVO1 == null || tagSortVO2 == null)
            return false;

       if(tagSortVO1.getFirstTagID() != null){
           if(tagSortVO1.getFirstTagID().equals(tagSortVO2.getFirstTagID())) pt += 5;
           else if(tagSortVO1.getFirstTagID().equals(tagSortVO2.getSecondTagID())) pt += 4;
                else if(tagSortVO1.getFirstTagID().equals(tagSortVO2.getThirdTagID())) pt += 3;
       }
        if(tagSortVO1.getSecondTagID() != null){
            if(tagSortVO1.getSecondTagID().equals(tagSortVO2.getFirstTagID())) pt += 4;
            else if(tagSortVO1.getSecondTagID().equals(tagSortVO2.getSecondTagID())) pt += 3;
            else if(tagSortVO1.getSecondTagID().equals(tagSortVO2.getThirdTagID())) pt += 2;
        }
        if(tagSortVO1.getThirdTagID() != null){
            if(tagSortVO1.getThirdTagID().equals(tagSortVO2.getFirstTagID())) pt += 3;
            else if(tagSortVO1.getThirdTagID().equals(tagSortVO2.getSecondTagID())) pt += 2;
            else if(tagSortVO1.getThirdTagID().equals(tagSortVO2.getThirdTagID())) pt += 1;
        }

       return pt >= 4;
    }


}
