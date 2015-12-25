package gui.room_with_order_display;

/**
 * Created by hyx on 2015/12/22.
 */
public enum Period {
    today("����"), in_30_days("��ʮ����"), this_week("�������"),
    next_week("�¸�����"), this_month("�����"), next_month("�¸���"),
    custom("�Զ���");

    String name;
    Period(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Period fromName(String name){
        if( name != null){
            for(Period p : Period.values()){
                if(p.getName().compareTo(name) == 0){
                    return p;
                }
            }
        }
        return null;
    }

}
