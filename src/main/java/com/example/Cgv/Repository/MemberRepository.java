package com.example.Cgv.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Cgv.DTO.MemberInfoDTO;
import com.example.Cgv.Entity.Member;

import jakarta.persistence.Entity;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	@Query(value ="select * from member where id=:id and pwd=:pwd", nativeQuery = true)
	Member findByIdAndPwd(@Param("id") String id, @Param("pwd") String pwd);
	
	@Query(value="select * from member where name=:name and bmonth=:bmonth and bday=:bday and tel1=:tel1 and tel2=:tel2 and tel3=:tel3", nativeQuery = true)
	Member findByNameAndBirthdayAndTel(@Param("name") String name,
											   @Param("bmonth") String bmonth,
											   @Param("bday") String bday,
											   @Param("tel1") String tel1,
											   @Param("tel2") String tel2,
											   @Param("tel3") String tel3);
	
	@Query(value="select * from member where id=:id and bmonth=:bmonth and bday=:bday and tel1=:tel1 and tel2=:tel2 and tel3=:tel3",nativeQuery = true)
	Member findByIdAndBirthdayAndTel(@Param("id") String id,
									 @Param("bmonth") String bmonth,
									 @Param("bday") String bday,
									 @Param("tel1") String tel1,
									 @Param("tel2") String tel2,
									 @Param("tel3") String tel3);
	
	@Query(value="select *  from member where id=:id",nativeQuery = true)
	Member findByIdforEmail(@Param("id") String id);
}
