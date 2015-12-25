package persistent;

import entity.Order;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by hyx on 2015/12/25.
 */
public interface IQueryOrderByRoom {
    /**
     * ��ѯ���й����ڸ��������Ҳ����ڡ��رա��͡���ɡ�״̬������<br/>
     * from��toʱ�������������ʱ�䷶Χ�������������ס�����֮������ʱ���������ʱ�䷶Χ���ص����֣����ⲿ�ֶ���Ӧ���ڷ��ؽ�����С�from��to������֤Ϊ��Ч������<br/>
     * roomType��ʾ�����ID���˲�������Ϊ��<br/>
     *
     * @param from              ��ѯ��ʼʱ��
     * @param to                ��ѯ��ֹʱ��
     * @param roomID          ����ID
     * @return �������������Ķ��������û��Ӧ�÷���һ�����б���������null
     */
    List<Order> queryActiveOrderByRoom(Timestamp from, Timestamp to,
                                long roomID);
}
