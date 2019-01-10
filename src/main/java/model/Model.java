package model;

import dao.BonusDao;
import dao.impl.BonusDaoImpl;

public class Model {
    public static void main(String[] args) {
        BonusDaoImpl bonusDao = new BonusDaoImpl();
        Bonus bonus = new Bonus();
        bonus =  bonusDao.getById(5);
        System.out.println(bonus);
    }
}
