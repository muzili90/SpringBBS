package cn.tiger.service.bbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cn.tiger.dao.bbs.BadgeDao;
import cn.tiger.dao.bbs.BadgeShowDao;
import cn.tiger.dao.bbs.CheckCommentAdDao;
import cn.tiger.dao.bbs.CheckTopicAdDao;
import cn.tiger.dao.bbs.CommentAdDao;
import cn.tiger.dao.bbs.CommentDao;
import cn.tiger.dao.bbs.CommunityLevelDao;
import cn.tiger.dao.bbs.NodeDao;
import cn.tiger.dao.bbs.TopicAdDao;
import cn.tiger.dao.bbs.TopicDao;
import cn.tiger.entity.bbs.Badge;
import cn.tiger.entity.bbs.BadgeShow;
import cn.tiger.entity.bbs.CheckCommentAd;
import cn.tiger.entity.bbs.CheckTopicAd;
import cn.tiger.entity.bbs.Comment;
import cn.tiger.entity.bbs.CommentAd;
import cn.tiger.entity.bbs.CommunityLevel;
import cn.tiger.entity.bbs.Node;
import cn.tiger.entity.bbs.Topic;
import cn.tiger.entity.bbs.TopicAd;

@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class BbsManager {

	private static Logger logger = LoggerFactory.getLogger(BbsManager.class);
	
	private TopicDao topicDao;

	private NodeDao nodeDao;
	
	private CommentDao commentDao;
	
	private TopicAdDao topicAdDao;
	
	private CheckTopicAdDao checkTopicAdDao;
	
	private CommentAdDao commentAdDao;
	
	private CheckCommentAdDao checkCommentAdDao;
	
	private CommunityLevelDao communityLevelDao;
	
	private BadgeDao badgeDao;
	
	private BadgeShowDao badgeShowDao;

	// 分页查询首页帖子
	@Transactional(readOnly = true)
	public Page<Topic> getIndexTopics(final Page<Topic> page) {
		return topicDao.findPageTopic(page);
	}
	
	// 分页查询子类帖子 new
	@Transactional(readOnly = true)
	public Page<Topic> getNodeTopics(final Page<Topic> page,Long nodeValue) {
		return topicDao.findPageNodeTopic(page,nodeValue);
	}
	
	//分页查询回复
	@Transactional(readOnly = true)
	public Page<Comment> getComments(final Page<Comment> page,List<PropertyFilter> filters) {
		return commentDao.findPage(page, filters);
	}
	
	//有序查询所有等级
	@Transactional(readOnly = true)
	public List<CommunityLevel> getAllCommunityLevels() {
		return communityLevelDao.getAll("levels",true);
	}
	
	//查询等级byId
	@Transactional(readOnly = true)
	public CommunityLevel getCommunityLevelById(Long id) {
		return communityLevelDao.get(id);
	}
	
	//查询等级byLevel
	@Transactional(readOnly = true)
	public CommunityLevel getCommunityLevelByLevel(int level) {
		return communityLevelDao.findUniqueBy("levels", level);
	}
	
	//查询徽章
	@Transactional(readOnly = true)
	public Badge getBadgeById(Long id) {
		return badgeDao.get(id);
	}
	
	//查询所有徽章
	@Transactional(readOnly = true)
	public List<Badge> getAllBadges() {
		return badgeDao.getAll();
	}
	
	//通过UserId和Badge查询用户显示的徽章
	@Transactional(readOnly = true)
	public BadgeShow getBadgeShowByUserIdAndBadgeId(Long userId,Long badgeId) {
		return badgeShowDao.findBadgeShowByUserIdAndBadgeId(userId,badgeId);
	}
	
	public void saveBadgeShow(BadgeShow badgeShow){
		badgeShowDao.save(badgeShow);
	}
	
	public void saveBadge(Badge badge){
		badgeDao.save(badge);
	}
	
	public void saveTopic(Topic topic){
		topicDao.save(topic);
	}
	
	public void deleteTopic(Long id){
		topicDao.delete(id);
	}
	
	public void saveTopicAd(TopicAd topicAd){
		topicAdDao.save(topicAd);
	}
	
	public void saveCommentAd(CommentAd commentAd){
		commentAdDao.save(commentAd);
	}
	
	public void saveComment(Comment comment){
		commentDao.save(comment);
	}
	
	public void saveCheckTopicAd(CheckTopicAd checkTopicAd){
		checkTopicAdDao.save(checkTopicAd);
	}
	
	public void saveCheckCommentAd(CheckCommentAd checkCommentAd){
		checkCommentAdDao.save(checkCommentAd);
	}
	
	@Transactional(readOnly = true)
	public Topic getTopic(Long topicId){
		return topicDao.get(topicId);
	}
	
	@Transactional(readOnly = true)
	public Comment getComment(Long commentId){
		return commentDao.get(commentId);
	}
	
	@Transactional(readOnly = true)
	public TopicAd getTopicAd(Long id){
		return topicAdDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public TopicAd getTopicAdByTopicId(Long topicId){
		return topicAdDao.findUniqueBy("topic.id", topicId);
	}
	
	@Transactional(readOnly = true)
	public CommentAd getCommentAd(Long id){
		return commentAdDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public CommentAd getCommentAdByCommentId(Long CommentId){
		return commentAdDao.findUniqueBy("comment.id", CommentId);
	}
	
	@Transactional(readOnly = true)
	public List<Node> findAllNodesByFitler(List<PropertyFilter> filters){
		return nodeDao.find(filters);
	}
	
	@Transactional(readOnly = true)
	public CheckTopicAd findCheckTopicAd(Long UserId,Long topicId){
		return checkTopicAdDao.getCheckTopicAd(UserId,topicId);
	}
	
	@Transactional(readOnly = true)
	public CheckCommentAd findCheckCommentAd(Long UserId,Long commentId){
		return checkCommentAdDao.getCheckCommentAd(UserId,commentId);
	}
	
	@Transactional(readOnly = true)
	public Node getNode(Long id){
		return nodeDao.get(id);
	}
	
	public TopicDao getTopicDao() {
		return topicDao;
	}
	
	@Autowired
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}
	
	public NodeDao getNodeDao() {
		return nodeDao;
	}

	@Autowired
	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}
	
	public CommentDao getCommentDao() {
		return commentDao;
	}

	@Autowired
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	public TopicAdDao getTopicAdDao() {
		return topicAdDao;
	}

	@Autowired
	public void setTopicAdDao(TopicAdDao topicAdDao) {
		this.topicAdDao = topicAdDao;
	}
	
	public CheckTopicAdDao getCheckTopicAdDao() {
		return checkTopicAdDao;
	}

	@Autowired
	public void setCheckTopicAdDao(CheckTopicAdDao checkTopicAdDao) {
		this.checkTopicAdDao = checkTopicAdDao;
	}

	public CommentAdDao getCommentAdDao() {
		return commentAdDao;
	}

	@Autowired
	public void setCommentAdDao(CommentAdDao commentAdDao) {
		this.commentAdDao = commentAdDao;
	}

	public CheckCommentAdDao getCheckCommentAdDao() {
		return checkCommentAdDao;
	}

	@Autowired
	public void setCheckCommentAdDao(CheckCommentAdDao checkCommentAdDao) {
		this.checkCommentAdDao = checkCommentAdDao;
	}

	public CommunityLevelDao getCommunityLevelDao() {
		return communityLevelDao;
	}

	@Autowired
	public void setCommunityLevelDao(CommunityLevelDao communityLevelDao) {
		this.communityLevelDao = communityLevelDao;
	}

	public BadgeDao getBadgeDao() {
		return badgeDao;
	}

	@Autowired
	public void setBadgeDao(BadgeDao badgeDao) {
		this.badgeDao = badgeDao;
	}

	public BadgeShowDao getBadgeShowDao() {
		return badgeShowDao;
	}

	@Autowired
	public void setBadgeShowDao(BadgeShowDao badgeShowDao) {
		this.badgeShowDao = badgeShowDao;
	}

}
