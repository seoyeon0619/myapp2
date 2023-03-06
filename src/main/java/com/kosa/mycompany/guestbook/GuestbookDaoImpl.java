package com.kosa.mycompany.guestbook;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/* root-context.xml
 <bean id="sm"   class="org.mybatis.spring.SqlSessionTemplate"> <!-- dao�� ���� -->
   <constructor-arg index="0"	ref="sqlSessionFactory" >
   </constructor-arg>
 </bean>
*/

@Repository("guestbookDao")
public class GuestbookDaoImpl implements GuestbookDao{
	// org.mybatis.spring.SqlSessionTemplate
	@Autowired
	SqlSessionTemplate sm;

	@Override
	public List<GuestbookDto> getList(GuestbookDto dto) {
		// ù��° �Ķ���� "Guestbook_getList" : mapper����(Guestbook.xml)���� ������ id�� ����
		// �ι�° �Ķ���� dto : parameterType�� ���޵�
		// selectList�Լ��� xml���Ͽ��� ������ �ҷ��� �����Ų�� 
		// ����� resultType���� ������ ��ü�� ����� ���� ���� �� ArrayList��ü�� �����
		// dto��ü �߰��� ��ȯ��
		return sm.selectList("Guestbook_getList", dto);
	}

	@Override
	public int getTotalCnt(GuestbookDto dto) {
		return sm.selectOne("Guestbook_getTotalCnt", dto);
	}

	@Override
	public GuestbookDto getView(GuestbookDto dto) {
		// �����Ͱ� �� ���� ��쿡�� selectOne�Լ� ȣ��
		return sm.selectOne("Guestbook_getView", dto);
	}

	@Override
	public void insert(GuestbookDto dto) {
		sm.insert("Guestbook_insert", dto);
	}

	@Override
	public void update(GuestbookDto dto) {
		sm.update("Guestbook_update", dto);
	}

	@Override
	public void delete(GuestbookDto dto) {
		sm.delete("Guestbook_delete", dto);
	}
	
}
