import com.learn.asm.MyClassLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xianglujun
 * @date 2023/4/27 11:44
 */
public class WriteByte {

    static String classStr = "����   4 �\u0001 norg/spring/cloud/alibaba/learn/nacos/config/configuration/ConfigConfiguration$$EnhancerBySpringCGLIB$$9c34f98c\u0007 \u0001\u0001 Morg/spring/cloud/alibaba/learn/nacos/config/configuration/ConfigConfiguration\u0007 \u0003\u0001 Worg/springframework/context/annotation/ConfigurationClassEnhancer$EnhancedConfiguration\u0007 \u0005\u0001 \u000B<generated>\u0001 \u000BCGLIB$BOUND\u0001 \u0001Z\u0001 \u0012CGLIB$FACTORY_DATA\u0001 \u0012Ljava/lang/Object;\u0001 \u0016CGLIB$THREAD_CALLBACKS\u0001 \u0017Ljava/lang/ThreadLocal;\u0001 \u0016CGLIB$STATIC_CALLBACKS\u0001 +[Lorg/springframework/cglib/proxy/Callback;\u0001 \u0010CGLIB$CALLBACK_0\u0001 3Lorg/springframework/cglib/proxy/MethodInterceptor;\u0001 \u0010CGLIB$CALLBACK_1\u0001 \u0010CGLIB$CALLBACK_2\u0001 &Lorg/springframework/cglib/proxy/NoOp;\u0001 \u0015CGLIB$CALLBACK_FILTER\u0001 \u0011CGLIB$STATICHOOK3\u0001 \u0003()V\u0001 \u0015java/lang/ThreadLocal\u0007 \u0018\u0001 \u0006<init>\f \u001A \u0017\n" +
            " \u0019 \u001B\f \f \n" +
            "\t \u0002 \u001D\u0001 \u001DCGLIB$setBeanFactory$5$Method\u0001 \u001ALjava/lang/reflect/Method;\u0001 \u001CCGLIB$setBeanFactory$5$Proxy\u0001 -Lorg/springframework/cglib/proxy/MethodProxy;\u0001 \u000FCGLIB$emptyArgs\u0001 \u0013[Ljava/lang/Object;\u0001 \u0016CGLIB$setBeanFactory$5\u0001 2(Lorg/springframework/beans/factory/BeanFactory;)V\u0001 (org/springframework/beans/BeansException\u0007 '\u0001 \u000EsetBeanFactory\f ) &\n" +
            " \u0004 *\f \u0012 \u0011\t \u0002 ,\u0001 \u0014CGLIB$BIND_CALLBACKS\u0001 \u0015(Ljava/lang/Object;)V\f . /\n" +
            " \u0002 0\f \u001F  \t \u0002 2\u0001 \u0010java/lang/Object\u0007 4\f ! \"\t \u0002 6\u0001 1org/springframework/cglib/proxy/MethodInterceptor\u0007 8\u0001 \tintercept\u0001 �(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;Lorg/springframework/cglib/proxy/MethodProxy;)Ljava/lang/Object;\f : ;\u000B 9 <\u0001 \u0015CGLIB$findMethodProxy\u0001 Y(Lorg/springframework/cglib/core/Signature;)Lorg/springframework/cglib/proxy/MethodProxy;\u0001 \btoString\u0001 \u0014()Ljava/lang/String;\f @ A\n" +
            " 5 B\u0001 \bhashCode\u0001 \u0003()I\f D E\n" +
            " 5 F\u0001 @setBeanFactory(Lorg/springframework/beans/factory/BeanFactory;)V\b H\u0001 \u0006equals\u0001 \u0015(Ljava/lang/Object;)Z\f J K\n" +
            " 5 L\u0001 \u0010java/lang/String\u0007 N\f # $\t \u0002 P\u0001 norg.spring.cloud.alibaba.learn.nacos.config.configuration.ConfigConfiguration$$EnhancerBySpringCGLIB$$9c34f98c\b R\u0001 \u000Fjava/lang/Class\u0007 T\u0001 \u0007forName\u0001 %(Ljava/lang/String;)Ljava/lang/Class;\f V W\n" +
            " U X\b )\b &\u0001 2org.springframework.beans.factory.BeanFactoryAware\b \\\u0001 \u0012getDeclaredMethods\u0001 \u001D()[Ljava/lang/reflect/Method;\f ^ _\n" +
            " U `\u0001 +org/springframework/cglib/core/ReflectUtils\u0007 b\u0001 \u000BfindMethods\u0001 K([Ljava/lang/String;[Ljava/lang/reflect/Method;)[Ljava/lang/reflect/Method;\f d e\n" +
            " c f\b %\u0001 +org/springframework/cglib/proxy/MethodProxy\u0007 i\u0001 \u0006create\u0001 �(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/springframework/cglib/proxy/MethodProxy;\f k l\n" +
            " j m\n" +
            " \u0004 \u001B\u0001 \u001ACGLIB$SET_THREAD_CALLBACKS\u0001 .([Lorg/springframework/cglib/proxy/Callback;)V\u0001 \u0003set\f r /\n" +
            " \u0019 s\u0001 \u001ACGLIB$SET_STATIC_CALLBACKS\f \u000E \u000F\t \u0002 v\f \b \t\t \u0002 x\u0001 \u0003get\u0001 \u0014()Ljava/lang/Object;\f z {\n" +
            " \u0019 |\u0007 \u000F\u0001 $org/springframework/cglib/proxy/NoOp\u0007 \u007F\f \u0013 \u0014\t \u0002 �\f \u0010 \u0011\t \u0002 �\u0001 \b<clinit>\u0001 \u0011CGLIB$STATICHOOK4\f � \u0017\n" +
            " \u0002 �\f \u0016 \u0017\n" +
            " \u0002 �\u0001 \u0013java/lang/Throwable\u0007 �\u0001 \n" +
            "$$beanFactory\u0001 /Lorg/springframework/beans/factory/BeanFactory;\u0001 \u0004Code\u0001 \n" +
            "StackMapTable\u0001 \n" +
            "Exceptions\u0001 \n" +
            "SourceFile \u0001 \u0002 \u0004 \u0001 \u0006 \f \u0002 \b \t   \t \n" +
            " \u000B   \u001A \f \n" +
            "   \u001A \u000E \u000F   \u0002 \u0010 \u0011   \u0002 \u0012 \u0011   \u0002 \u0013 \u0014   \n" +
            " \u0015 \u000B   \u001A \u001F     \u001A ! \"   \u001A # $   \u0001 � �   \n" +
            " \b \u0016 \u0017 \u0001 �   d \u0006 \u0002   I� \u0019Y� \u001C� \u001E\u0003� 5� Q\u0012S� YK\u0005� OY\u0003\u0012ZSY\u0004\u0012[S\u0012]� YYL� a� gY\u00032� 3+*\u0012[\u0012Z\u0012h� n� 7W��   \u0001 �   \t \u0002 � G\u0007 � \u0010 % & \u0002 �   \u0012 \u0002 \u0002   \u0006*+� +�     �   \u0004 \u0001 ( \u0011 ) & \u0002 �   L \u0007 \u0002   0*� -Y� \fW*� 1*� -Y� \u0018*� 3\u0004� 5Y\u0003+S� 7� =\u0005 �*+� +�   \u0001 �   \n" +
            " \u0002Q\u0007 9X\u0007 9 �   \u0004 \u0001 ( \t > ? \u0001 �   H \u0002 \u0001   +*� CY� G�       |�ڄ|�ڄ   \u0014\u0012I� M� \b� 7�W\u0001�   \u0001 �   \u000B \u0003\\\u0007 OK\u0007 O  \u0001 \u001A \u0017 \u0001 �   \u0015 \u0002 \u0001   \t*Y� o� 1�     \t p q \u0001 �   \u0014 \u0002 \u0001   \b� \u001E*� t�     \t u q \u0001 �   \u0011 \u0001 \u0001   \u0005*� w�     \u001A . / \u0001 �   l \u0005 \u0002   G*� \u0002L+� y� =+\u0004� y� \u001E� }Y� \u000FW� wY� \u0007W� \"� ~+_\\\u00052� �� �\\\u00042� 9� -\u00032� 9� ��   \u0001 �   \u0013 \u0002� ' \u0002\u0007 5\u0007 \u0002 \u0001\u0007 5\u001E \b � \u0017 \u0001 �   \u0013       \u0007� �� ��     \b � \u0017 \u0001 �   \n" +
            "       \u0001�     \u0001 �   \u0002 \u0007";

    public static void main(String[] args) throws IOException {
        byte[] bytes = classStr.getBytes();
        FileOutputStream fos = new FileOutputStream(new File("d:/cglib.class"));
        fos.write(bytes);
        fos.flush();
        fos.close();

        MyClassLoader classLoader = new MyClassLoader();
        classLoader.defineClass("org/spring/cloud/alibaba/learn/nacos/config/configuration/ConfigConfiguration$$EnhancerBySpringCGLIB$$9c34f98c"
                .replaceAll("/", "."), bytes);
    }
}
