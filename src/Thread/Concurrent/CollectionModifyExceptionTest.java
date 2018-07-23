package Thread.Concurrent;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
public class CollectionModifyExceptionTest {
	public static void main(String[] args) {
		// 该集合在迭代的时候可以实现其他的操作 而普通的集合就不行 new ArrayList();
		Collection users = new CopyOnWriteArrayList();

		users.add(new User("fxb1",28));
		users.add(new User("fxb2",25));
		users.add(new User("fxb3",31));
		Iterator itrUsers = users.iterator();
		while(itrUsers.hasNext()){
			System.out.println("----");
			User user = (User)itrUsers.next();
			if("fxb2".equals(user.getName())){
				users.remove(user);
				//itrUsers.remove();
			} else {
				System.out.println(user);				
			}
		}
	}
}	 
