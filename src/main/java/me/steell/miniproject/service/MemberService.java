package me.steell.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.steell.miniproject.domain.Member;
import me.steell.miniproject.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public Long join(final Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(final Member member) {
        // exception
        final List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(final Long memberId) {
        return memberRepository.findOne(memberId);
    }
}