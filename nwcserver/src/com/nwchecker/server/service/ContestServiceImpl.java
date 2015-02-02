/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nwchecker.server.service;

import com.nwchecker.server.dao.ContestDAO;
import com.nwchecker.server.model.Contest;
import com.nwchecker.server.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Роман
 */
@Service
public class ContestServiceImpl implements ContestService {

    @Autowired
    private UserService userService;

    @Autowired
    private ContestDAO contestDAO;

    @Transactional
    @Override
    public void addContest(Contest c) {
        contestDAO.addContest(c);
    }

    @Transactional
    @Override
    public void updateContest(Contest c) {
        contestDAO.updateContest(c);
    }

    @Transactional
    @Override
    public void mergeContest(Contest c) {
        contestDAO.mergeContest(c);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contest> getContests() {
        return contestDAO.getContests();
    }

    @Transactional(readOnly = true)
    @Override
    public Contest getContestByID(int id) {
        return contestDAO.getContestByID(id);
    }

    @Override
    public boolean checkIfUserHaveAccessToContest(String username, int ContestId) {
        User teacher = userService.getUserByUsername(username);
        if ((teacher.getContest() != null) && (teacher.getContest().size() > 0)) {
            for (Contest c : teacher.getContest()) {
                if (c.getId() == ContestId) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void setContestDAO(ContestDAO dao) {
        contestDAO = dao;
    }

    public UserService getUserService() {
        return userService;
    }

    @Override
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
