#include <jni.h>
#include <stdlib.h>
//��ȡѹ���ķ���
int getPressure(){
	return  rand()%801;
}

int  flag = 1;

JNIEXPORT void JNICALL Java_com_onezao_onezao_pressurediagram0130_PressureDiagramActivity_startMonitor
  (JNIEnv * env, jobject  obj){
       //1.��ȡ�ֽ���
	jclass  clazz  = (*env) -> FindClass(env,"com/onezao/onezao/pressurediagram0130/PressureDiagramActivity");
	//2.�ҵ�����
	jmethodID  methodID = (*env) -> GetMethodID(env,clazz,"setPressure","(I)V");
	//3. ���÷���
	flag =1 ;
	while(flag){
		//��Linux�У�sleep   1  ���Ǵ���˯һ���ӡ�
		sleep(1);
	(*env) -> CallVoidMethod(env,obj,methodID,getPressure());
        }
}

/*
 * Class:     com_onezao_onezao_pressurediagram0130_PressureDiagramActivity
 * Method:    stopMonitor
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_onezao_onezao_pressurediagram0130_PressureDiagramActivity_stopMonitor
  (JNIEnv * env, jobject  obj){
             flag =0;
}
