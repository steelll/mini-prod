package me.steell.miniproject.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import me.steell.miniproject.domain.Member;
import me.steell.miniproject.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository MemberRepository;

    @Test
    @Rollback(false)
    public void 가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("steell");
        
        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, MemberRepository.findOne(savedId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("steell");

        Member member2 = new Member();
        member1.setName("steell");

        //when
        memberService.join(member1);
        memberService.join(member2);
        
        //then
        fail("예외발생해야 함");

    }
}