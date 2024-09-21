package com.example.Cgv.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Cgv.DTO.MemberInfoDTO;
import com.example.Cgv.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	@Query(value ="select * from member where id=:id and pwd=:pwd", nativeQuery = true)
	Member findByIdAndPwd(@Param("id") String id, @Param("pwd") String pwd);
	
	@Query(value="select * from member where name=:name and byear=:byear and bmonth=:bmonth and bday=:bday and tel1=:tel1 and tel2=:tel2 and tel3=:tel3", nativeQuery = true)
	Member findByNameAndBirthdayBirthdayAndTel(@Param("name") String name,
											   @Param("byear") String byear,
											   @Param("bmonth") String bmonth,
											   @Param("bday") String bday,
											   @Param("tel1") String tel1,
											   @Param("tel2") String tel2,
											   @Param("tel3") String tel3);
}
