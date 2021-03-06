package com.example.YOURPROJECT;

import org.hibernate.Session;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Query;

public class TreeDao {


    private static volatile TreeDao instance = null;
    private TreeDao() { }

    public static synchronized TreeDao getInstance() {
        if (instance == null) {
            instance = new TreeDao();
        }
        return instance;
    }

    public void deleteAllTrees() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete from Tree").executeUpdate();
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    public List<Tree> getAllTrees(double longitud, double latitude, int numberOfTrees) {
        Session session = HibernateUtil.getSession();
        Query query = session.createSQLQuery("SELECT * FROM Tree " +
                "ORDER BY ST_Distance(Tree.location, ST_Geomfromtext('POINT(" + longitud + " " + latitude + ")',4326)) " +
                "limit " + numberOfTrees).addEntity(Tree.class);
        List<Tree> trees = query.list();
        session.flush();
        session.close();
        return trees;
    }

    public  List<Tree> getAllTrees(){
        return getAllTrees(0,0,7);
    }
    public int countTotalTreesInGridPoint(float longitude,float latitude){
        //0.0001 => 10m
        float minLongitude = (float)(Math.floor(longitude * 10000)/10000);
        float minLatitude =  (float)(Math.floor(latitude * 10000)/10000);

        Session session = HibernateUtil.getSession();
        String query = "SELECT COUNT(*)" +
                "FROM Tree " +
                "WHERE " +
                "Tree.location && " +
                "ST_MakeEnvelope("+minLongitude+", "+minLatitude+", "+ (minLongitude+0.0001) +", "+ (minLatitude+0.0001) +", 4326) ";
        Integer total = ((BigInteger) session.createSQLQuery(query).uniqueResult()).intValue();
        session.flush();
        session.close();
        return total;
    }
    public Tree getTree(int id) {
        Session session = HibernateUtil.getSession();
        Tree tree = (Tree) session.get(Tree.class, id);
        session.close();
        return tree;
    }

    public void save(Tree tree) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(tree);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    public Tree deleteTree(int id) {
        EntityManager em = HibernateUtil.createEntityManager();
        Tree tree = getTree(id);
        try {
            Session session = HibernateUtil.getSession();
            session.createQuery("delete from Tree where id="+ id).executeUpdate();
            session.close();
            return tree;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
