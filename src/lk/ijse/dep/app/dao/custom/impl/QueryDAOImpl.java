package lk.ijse.dep.app.dao.custom.impl;


import lk.ijse.dep.app.dao.custom.QueryDAO;
import lk.ijse.dep.app.entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QueryDAOImpl implements QueryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CustomEntity> findOrderDetailsWithItemDescriptions(String orderId) throws Exception {
        List<Object[]> list=getSession().createQuery("SELECT o.orderDetailPK.itemCode,o.qty,o.unitPrice,i.description FROM OrderDetail o INNER JOIN o.item i where o.orderDetailPK.orderId=?1").setParameter(1, orderId).list();
        ArrayList<CustomEntity> customEntityArrayList = new ArrayList<>();
        for(Object[] arr : list){
            customEntityArrayList.add(new CustomEntity((String)arr[0],(int)arr[1],(double)arr[2],(String) arr[3]));
            System.out.println( Arrays.toString(arr)+"  ad@miayyy  "+arr[1]);
        }
        return customEntityArrayList;
    }

    @Override
    public List<CustomEntity> findAllOrdersWithCustomerNameAndTotal() throws Exception {
//        ArrayList<CustomEntity> list1 = new ArrayList<>();
//        String startDate="01-02-2013";
//        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
//        java.util.Date date = sdf1.parse(startDate);
//        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
//
//        list1.add(new CustomEntity("1"	,sqlStartDate,"c001","c001",3423));

//        List<CustomEntity> list = session.createQuery("SELECT o.id, o.date, o.customerId, C.name,\n" +
//                "       SUM(Detail.qty * Detail.unitPrice) AS Total FROM Order AS o \n" +
//                " INNER JOIN Customer C on o.customerId = C.id \n" +
//                "INNER JOIN OrderDetail Detail on o.id = Detail.orderId GROUP BY o.id", CustomEntity.class).list();
//
//        System.out.println("imall"+list);
        ///////////////////////////////////////////////

        Query query = getSession().createQuery("select e.orderDetailPK.orderId,o.date,o.customer.id,o.customer.name,e.qty*e.unitPrice from OrderDetail e INNER JOIN e.item a INNER JOIN e.order o");
        List<Object[]> list = query.list();
        ArrayList<CustomEntity> customEntityArrayList = new ArrayList<>();

        for(Object[] arr : list){
            customEntityArrayList.add(new CustomEntity((String)arr[0],(Date)arr[1],(String)arr[2],(String)arr[3],(double)arr[4]));
          System.out.println( Arrays.toString(arr)+"  ad@mia  "+arr[1]);
        }

        //////////////////////////////////////////////////



        return customEntityArrayList;
    }


    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
