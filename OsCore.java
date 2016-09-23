publc las
<bean id="paymentServer" class="org.jpos.iso.ISOServer">
    <constructor-arg>
        <value>13000</value>
    </constructor-arg>
    <constructor-arg ref="paymentChannel" />
    <constructor-arg ref="paymentServerThreadPool" />
</bean>

<bean id="paymentChannel" class="org.jpos.iso.channel.ASCIIChannel">
    <constructor-arg ref="paymentPackager" />
</bean>

<bean id="paymentPackager" class="com.sample.payment.packager.PaymentPackager"/>

<bean id="paymentServerThreadPool" class="org.jpos.util.ThreadPool">
    <constructor-arg>
        <value>1</value>
    </constructor-arg>
    <constructor-arg>
        <value>100</value>
    </constructor-arg>
    <constructor-arg>
        <value>PaymentServer</value>
    </constructor-arg>
</bean>

<bean id="paymentProcessor" class="com.sample.processors.PaymentProcessor"  init-method="init"/>