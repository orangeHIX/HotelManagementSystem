package business.inter;

import business.NoFilterConditionException;
import business.entity.RoomInfo;
import business.entity.RoomWithOrder;

import java.util.Calendar;
import java.util.List;


/**
 * �������ڴӷ����ʱ��εĽǶȷ����ѯ����͹����Ķ�����
 * ���ݷ�������ʵ�����۵��Ƿ����һ��ʱ��ʹ��Ȩ������ʱ��
 * ͨ�����������ҹ���������Ķ����Ϳ���֪��ĳ��ʱ����������䷿ʱ������״�����������������ͨ�������Ķ������ֵġ�
 * �������ַ�ʽ��ѯ���ķ����������Ķ������ṩ��Ԥ������ס�����Ȼ���ҵ��ı�Ҫ��Ϣ��
 * �����ṩ���ɸ�ɸѡ������ɸѡ����ͷ�������Ķ�����
 * Created by hyx on 2015/12/16.
 */
public interface IRoomWithOrderDisplay {

    /**
     * �˷�����ȡ��ʱ���ҵ�ʱ�䷶Χ����ɸѡ����Ϊ��Ҫɸѡ������
     * ɸѡ��ʼʱ����ɸѡ��ֹʱ��㱣֤Ϊ��Чʱ��㡣
     * ���ĳ����������Ķ�������סʱ������ʱ���ȡ��ʱ���ȫ�����߲����ڲ��ҵ�ʱ�䷶Χ�ڣ���ô�ö�������Ϊ����ɸѡ����
     *
     * @param startDate   ɸѡ��ʼʱ���
     * @param closingDate ɸѡ��ֹʱ���
     */
    void setTimeFilterConditions(Calendar startDate, Calendar closingDate);

    /**
     * �˷�������ɸѡ�����ķ��䡣��ɸѡ����Ϊ���Ǳ�Ҫ�ġ�
     * ���������ϲ����а�����ɸѡ��Ϣ����ô�÷��䱻��Ϊ����ɸѡ����
     *
     * @param roomInfo ���ڷ����ɸѡ��Ϣ�����roomInfo��ĳ����Ϣ�ֶ�Ϊnull����ʾ���ô���Ϣ�ν���ɸѡ��
     */
    void setRoomInfoFilterConditions(RoomInfo roomInfo);

    /**
     * ���ҷ�������ɸѡ�����ķ��估��Щ�������ɸѡ�����Ķ�������Щ�������������ǹ����ķ�����࣬ÿ����������Ķ�������ʱ����Ⱥ�˳�����С�
     * ÿ�ε��ø÷�������ѯ������Ϸ��ϵ�ǰ���õ�ɸѡ������
     * ���û������ĳЩ��Ҫɸѡ���������׳�NoFilterConditionException�쳣���쳣��Ϣָ����Щ��Ҫɸѡ����û�����á�
     *
     * @return ���������ķ��估���ǹ��������򶩵��������ѯ���Ϊ�գ�����null
     */
    List<RoomWithOrder> searchRoomWithOrder() throws NoFilterConditionException;
}
