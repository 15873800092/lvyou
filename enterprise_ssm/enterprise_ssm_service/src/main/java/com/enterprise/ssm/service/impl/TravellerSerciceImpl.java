package com.enterprise.ssm.service.impl;

import com.enterprise.ssm.dao.ITravellerDao;
import com.enterprise.ssm.domain.Traveller;
import com.enterprise.ssm.service.ITravellerService;
import com.enterprise.ssm.utils.MailUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellerSerciceImpl implements ITravellerService {

    @Autowired
    private ITravellerDao travellerDao;

    @Override
    public List<Traveller> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return travellerDao.findAll();
    }

    @Override
    public void deleteTravellerById(Integer travellerId) throws Exception {

        travellerDao.deleteTravellerById(travellerId);
    }

    @Override
    public void deleteTravellerByIds(Integer[] taravellersId) throws Exception {
        for(Integer travellerId:taravellersId){

            travellerDao.deleteTravellerById(travellerId);
        }
    }

    @Override
    public Traveller findById(Integer travellerId) throws Exception {
        return travellerDao.findById(travellerId);
    }

    @Override
    public void update(Traveller traveller) throws Exception {
        traveller=exchange(traveller);
        travellerDao.update(traveller);
    }

    @Override
    public Traveller findNameAndPwd(String name, String password) throws Exception {
        Traveller traveller=travellerDao.findNameAndPwd(name,password);
        return traveller;
    }

    @Override
    public void save(Traveller traveller) throws Exception {
        traveller=exchange(traveller);
        traveller.setStatus("N");
        travellerDao.save(traveller);
        String context="<a href='http://localhost:8080/enterprise_ssm_web/traveller/active.do?tid="+traveller.getTid()+"'>点击激活【旅游网】</a>";
        MailUtils.sendMail(traveller.getEmail(),context,"激活邮件");
    }

    @Override
    public void active(Integer tid) throws Exception {
        Traveller traveller=travellerDao.findById(tid);
        traveller.setStatus("Y");
        travellerDao.update(traveller);
    }


    public Traveller exchange(Traveller traveller){
        if(traveller.getTravellerTypeStr()!=null){
            if(traveller.getTravellerTypeStr()=="儿童"){
                traveller.setTravellerType(0);
            }
            if(traveller.getTravellerTypeStr()=="成人"){
                traveller.setTravellerType(1);
            }
        }
        if(traveller.getCredentialsTypeStr()!=null){
            if(traveller.getCredentialsTypeStr()=="身份证"){
                traveller.setCredentialsType(0);
            }else if(traveller.getCredentialsTypeStr()=="护照"){
                traveller.setCredentialsType(1);
            }else if(traveller.getCredentialsTypeStr()=="军官证"){
                traveller.setCredentialsType(2);
            }
        }
        return  traveller;
    }
}
