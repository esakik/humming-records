package com.application.humming.logic.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.application.humming.dao.MemberDao;
import com.application.humming.entity.MemberEntity;
import com.application.humming.exception.HummingException;

@RunWith(SpringJUnit4ClassRunner.class)
public class MemberLogicImplTest {

    @InjectMocks
    private MemberLogicImpl sut;
    @Mock
    private MemberDao memberDaoMock;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private MemberEntity memberEntity;

    private static final int ID = 1;
    private static final String NAME = "田中太郎";
    private static final String EMAIL = "taro.tanaka@email";
    private static final String PASSWORD = "tanakataro";
    private static final String ADDRESS = "東京都杉並区高円寺";
    private static final String TELEPHONE = "080-0000-0000";

    @Before
    public void setUp() {
        memberEntity = MemberEntity.builder()
                .id(ID)
                .name(NAME)
                .password(PASSWORD)
                .address(ADDRESS)
                .telephone(TELEPHONE)
                .build();
    }

    @Test
    public void findByEmailTest_Success() {
        final MemberEntity expected = memberEntity;
        when(memberDaoMock.findByEmail(EMAIL)).thenReturn(memberEntity);
        final MemberEntity actual = sut.findByEmail(EMAIL);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void findByEmailTest_DataNotFound() {
        when(memberDaoMock.findByEmail(EMAIL)).thenReturn(null);
        final MemberEntity actual = sut.findByEmail(EMAIL);
        assertThat(actual, nullValue());
    }

    @Test
    public void saveTest_SuccessToUpdate() throws HummingException {
        doNothing().when(memberDaoMock).save(memberEntity);
        sut.save(memberEntity);
        verify(memberDaoMock, times(1)).save(any());
    }

    @Test
    public void saveTest_SuccessToInsert() throws HummingException {
        memberEntity.setId(null);
        doNothing().when(memberDaoMock).save(memberEntity);
        sut.save(memberEntity);
        verify(memberDaoMock, times(1)).save(any());
    }

    @Test
    public void saveTest_Fail() throws HummingException {
        thrown.expect(HummingException.class);
        doThrow(HummingException.class).when(memberDaoMock).save(memberEntity);
        sut.save(memberEntity);
        verify(memberDaoMock, times(1)).save(any());
    }

    @Test
    public void deleteTest_Success() throws HummingException {
        doNothing().when(memberDaoMock).delete(ID);
        sut.delete(ID);
        verify(memberDaoMock, times(1)).delete(any());
    }

    @Test
    public void deleteTest_Fail() throws HummingException {
        thrown.expect(HummingException.class);
        doThrow(HummingException.class).when(memberDaoMock).delete(ID);
        sut.delete(ID);
        verify(memberDaoMock, times(1)).delete(any());
    }

}
