package jp.wsf.jDriver.test;
import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Iterator;

public class testGetMothods {
	public static void main(String[] args) {
		Human human = new Human("Hoge");
		Method[] methods = human.getClass().getDeclaredMethods();
		/*String TargetName = "";
		int paramNum = 0;
		ArrayList<String> paramTypes = new ArrayList<String>();
		String targetMethod = "setAge";
*/
		for (Method method : methods) {
			System.out.println("MethodName:" + method.getName());
			System.out.println("params:" + method.getParameterTypes().length);
			for (Class paramtype : method.getParameterTypes()) {
				System.out.println(paramtype.getSimpleName());
			}

			/*if (method.getName().equals(targetMethod)) {
				TargetName = method.getName();
				paramNum = method.getParameterTypes().length;
				for (Class paramtype : method.getParameterTypes()) {
					paramTypes.add(paramtype.getSimpleName());
				}
			}*/
		}

		/*System.out.println("MethodName:" + TargetName);
		System.out.println("params:" + paramNum);
		Iterator<String> ite = paramTypes.iterator();
		while (ite.hasNext()) {
			System.out.println("Type:" + ite.next());

		}*/
	}
}
