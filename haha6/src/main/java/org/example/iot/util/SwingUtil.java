package org.example.iot.util;

import java.net.URL;
import java.awt.Point;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.ImageIcon;

/**Title: SwingUtil.java
 * Swing������
 * 
 * @author Run
 * @date  2019-08-20 */
public class SwingUtil {
        
    /**����һ����������Ӧ�����С��ImageIcon����
     * @param image ��<code> Image </code>����������ImageIcon
     * @param constrained �Ƿ�ȱ������� ����Ϊ<code> true </code>ʱ����ͨ��
     *      {@link javax.swing.JComponent#setAlignmentX(float)}��
     *      {@link javax.swing.JComponent#setAlignmentY(float)}��������������뷽ʽ��
     * @date  2019-08-20 */
    public static ImageIcon createAutoAdjustIcon(Image image, boolean constrained) {
        ImageIcon icon = new ImageIcon(image) {
            @Override
            public synchronized void paintIcon(java.awt.Component cmp, Graphics g, int x, int y) {
                //��ʼ������
                Point startPoint = new Point(0, 0);//Ĭ�ϻ������
                Dimension cmpSize = cmp.getSize();//��ȡ�����С
                Dimension imgSize = new Dimension(getIconWidth(), getIconHeight());//��ȡͼ���С
                
                //���������������
                if(constrained) {//�ȱ�������
                    //����ͼ���߱���
                    double ratio = 1.0*imgSize.width/imgSize.height;
                    //����ȱ������ź�������С
                    imgSize.width = (int) Math.min(cmpSize.width, ratio*cmpSize.height);
                    imgSize.height = (int) (imgSize.width/ratio);
                    //����������
                    startPoint.x = (int) 
                            (cmp.getAlignmentX()*(cmpSize.width - imgSize.width));
                    startPoint.y = (int) 
                            (cmp.getAlignmentY()*(cmpSize.height - imgSize.height));
                } else {//��ȫ���
                    imgSize = cmpSize;
                }
                
                //�������������С���л���
                if(getImageObserver() == null) {
                    g.drawImage(getImage(), startPoint.x, startPoint.y,
                            imgSize.width, imgSize.height, cmp);
                 } else {
                    g.drawImage(getImage(), startPoint.x, startPoint.y,
                            imgSize.width, imgSize.height, getImageObserver());
                 }
            };
        };
        return icon;
    }
    
    /**����һ����������Ӧ�����С��Icon����
     * @param filename ָ���ļ�������·�����ַ���
     * @param constrained �Ƿ�ȱ������š���Ϊ<code> true </code>ʱ����ͨ��
     *      {@link javax.swing.JComponent#setAlignmentX(float)}��
     *      {@link javax.swing.JComponent#setAlignmentY(float)}��������������뷽ʽ��
     * @date  2019-08-20 */
    public static ImageIcon createAutoAdjustIcon(String filename, boolean constrained) {
        return createAutoAdjustIcon(new ImageIcon(filename).getImage(), constrained);
    }
    
    /**����һ����������Ӧ�����С��ImageIcon����
     * @param url ��ָ����<code> URL </code>����������ImageIcon
     * @param constrained �Ƿ�ȱ������� ����Ϊ<code> true </code>ʱ����ͨ��
     *      {@link javax.swing.JComponent#setAlignmentX(float)}��
     *      {@link javax.swing.JComponent#setAlignmentY(float)}��������������뷽ʽ��
     * @date  2019-08-20 */
    public static ImageIcon createAutoAdjustIcon(URL url, boolean constrained) {
        return createAutoAdjustIcon(new ImageIcon(url).getImage(), constrained);
    }
    
}

