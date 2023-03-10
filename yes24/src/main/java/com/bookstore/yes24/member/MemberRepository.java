package com.bookstore.yes24.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findByMemberName(String memberName, Pageable pageable);

}
