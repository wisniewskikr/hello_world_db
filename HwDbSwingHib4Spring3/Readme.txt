FROM HIBERNATE 3 TO HIBERNATE 4



I. POM.XML

1. Improve spring and hibernate versions

<properties>
		<spring.version>3.1.2.RELEASE</spring.version>	
		<hibernate.version>4.1.7.Final</hibernate.version>	
	</properties>
	
	
2. Add hibernate 4 dependencies

<!-- Hibernate dependencies -->
 <dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>${hibernate.version}</version>
</dependency>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-entitymanager</artifactId>
    <version>${hibernate.version}</version>
</dependency> 
        

3. Add cglib dependencies

<dependency>
    <groupId>cglib</groupId>
	<artifactId>cglib</artifactId>
	<version>2.2</version>
</dependency>



II. SPRING CONFIGURATION        

1. Change session factory in main and test configuration files

From
org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean
To
org.springframework.orm.hibernate4.LocalSessionFactoryBean


2. Change hibernate transacion manager in main and test configuration files

From 
org.springframework.orm.hibernate3.HibernateTransactionManager
To
org.springframework.orm.hibernate4.HibernateTransactionManager



III. MAIN CODE	

1. Make all method in all services transactional
@Transactional	



IV. TEST CODE

1. Remove DbUnit helper methods
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    						DbUnitTestExecutionListener.class })
and							
@DatabaseSetup("/dbunit/input.xml")

2. Implement your own dbunit methods	