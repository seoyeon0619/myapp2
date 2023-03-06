package com.kosa.mycompany.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	SqlSessionTemplate sm; // ���� ��������, Mybatis�����ӿ�ũ
	@Override
	public void insert(MemberDto dto) {
		sm.insert("Member_insert", dto);
	}
	@Override
	public boolean isDuplicate(MemberDto dto) {
		int result = sm.selectOne("Member_idcheck", dto);
		if(result==0) // ��� ����
			return false;
		else // �̹� �����
			return true;
	}
	@Override
	public MemberDto logon(MemberDto dto) {
		return sm.selectOne("Member_login", dto);
	}
	@Override
	public MemberDto findid(MemberDto dto) {
		return sm.selectOne("Member_findid", dto);
	}
	@Override
	public MemberDto findpassword(MemberDto dto) {
		return sm.selectOne("Member_findpassword", dto);
	}
	@Override
	public List<MemberDto> getList(MemberDto dto) {
		return sm.selectList("Member_listdata", dto);
	}
	@Override
	public int getTotalCnt(MemberDto dto) {
		return sm.selectOne("Member_totalCnt", dto);
	}
	

}
