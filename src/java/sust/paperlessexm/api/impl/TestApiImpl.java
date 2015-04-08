/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sust.paperlessexm.api.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import sust.paperlessexm.api.TestApi;
import sust.paperlessexm.bean.TestBean;
import sust.paperlessexm.entity.Test;
import sust.paperlessexm.exception.GenericBusinessException;
import sust.paperlessexm.session.TestService;

/**
 *
 * @author Jagothjyoti
 */
public class TestApiImpl implements TestApi {

    @Override
    public TestBean addTest(TestBean model) throws GenericBusinessException {
        Test test = new TestBean().toEntity(model);
        TestService testService = new TestService();
        Test test1 = testService.addTest(test);
        return getTest(test1.getPrimaryKey());
    }

    @Override
    public void saveTest(TestBean model) throws GenericBusinessException {
        Test test = new TestBean().toEntity(model);
        TestService testService = new TestService();
        testService.saveTest(test);
    }

    @Override
    public void deleteTest(Integer id) throws GenericBusinessException {
        TestService testService = new TestService();
        testService.deleteTest(id);
    }

    @Override
    public TestBean getTest(Integer id) throws GenericBusinessException {
        TestService testService = new TestService();
        Test test = testService.getTest(id);
        return new TestBean(test);
    }

    @Override
    public List<TestBean> getTestList() throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.getTestList();
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> getTestList(int startIndex, int endIndex) throws GenericBusinessException {
        if (startIndex < 1) {
            startIndex = 1;
        }
        if (endIndex - startIndex < 0) {
            return new ArrayList<>();
        }

        TestService testService = new TestService();
        List list1 = testService.getTestList(startIndex, endIndex);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public int getTestListSize() throws GenericBusinessException {
        TestService testService = new TestService();
        return testService.getTestListSize();
    }

    @Override
    public List<TestBean> findTestByTestId(Integer testId) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByTestId(testId);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> findTestByMarks(Integer marks) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByMarks(marks);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> findTestByTestTitle(Integer testTitle) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByTestTitle(testTitle);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> findTestByCreateDate(Date createDate) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByCreateDate(createDate);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> findTestByUpdateDate(Date updateDate) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByUpdateDate(updateDate);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> findTestByTestTime(Timestamp testTime) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByTestTime(testTime);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> findTestByTestDuration(Integer testDuration) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByTestDuration(testDuration);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> findTestByDescription(String description) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByDescription(description);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> findTestByTestNo(Integer testNo) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByTestNo(testNo);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> findTestByCourseId(Integer courseId) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByCourseId(courseId);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public List<TestBean> findTestByTeachesId(Integer teachesId) throws GenericBusinessException {
        TestService testService = new TestService();
        List list1 = testService.findTestByTeachesId(teachesId);
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list.add(new TestBean((Test) list1.get(i)));
        }
        return list;
    }

    @Override
    public int findTestNo(Integer courseId) throws GenericBusinessException {
        TestService testService = new TestService();
        List<Test> list1 = testService.findTestByCourseId(courseId);
        if (list1.isEmpty()) {
            return 0;
        }

//        Collections.sort(list1,new Comparator<Test>(){
//
//            @Override
//            public int compare(Test o1, Test o2) {
//                return 1;
//            }
//        });
        //int index = list1.size() - 1;
        
        int mxTestNo = 0;
        //Test t = new Test();
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getTestNo() > mxTestNo) {
                //t = list1.get(i);
                mxTestNo=list1.get(i).getTestNo();
            }
        }
        
        return mxTestNo+1;
    }
}
