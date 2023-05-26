package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Host;
import xyz.itwill.dto.Pay;
import xyz.itwill.dto.SpacePayHost;
import xyz.itwill.mapper.AdminMapper;

@Repository
@RequiredArgsConstructor
public class AdminDAOImpl implements AdminDAO {
	private final SqlSession sqlSession;

	@Override
	public int selectnonpayCount() {
		return sqlSession.getMapper(AdminMapper.class).selectnonpayCount();
	}

	@Override
	public Host selectadminHostspace(int sNo) {
		return sqlSession.getMapper(AdminMapper.class).selectadminHostspace(sNo);
	}

	@Override
	public int updateadminPay(Pay pay) {
		return sqlSession.getMapper(AdminMapper.class).updateadminPay(pay);
	}

	@Override
	public List<SpacePayHost> selectadminPayspaceList(Map<String, Object> map) {
		return sqlSession.getMapper(AdminMapper.class).selectadminPayspaceList(map);
	}

	@Override
	public List<SpacePayHost> selectstatusPayspaceList(Map<String, Object> map) {
		return sqlSession.getMapper(AdminMapper.class).selectstatusPayspaceList(map);
	}

	@Override
	public List<SpacePayHost> selectSearchList(Map<String, Object> map) {
		return sqlSession.getMapper(AdminMapper.class).selectSearchList(map);
	}

}
