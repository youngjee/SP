package file.list;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

//파일값을 List<VO>에 쓰고 정렬,출력하기
public class ListOrder {
	public static void main(String[] args) throws IOException {

		List<UserInfo> users = getUsers("./DS_Sample1.txt");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String str = br.readLine();
			if (str.equals("quit")) {
				break;
			} else if (str.equals("korean")) {
				Collections.sort(users, new Comparator<UserInfo>() {
					@Override
					public int compare(UserInfo o1, UserInfo o2) {
						if (o1.getkGrade() > o2.getkGrade()) {
							return 1;
						} else if (o1.getkGrade() < o2.getkGrade()) {
							return -1;
						} else {
							return 0;
						}
					}
				});
			} else if (str.equals("english")) {
				Collections.sort(users, new Comparator<UserInfo>() {
					@Override
					public int compare(UserInfo o1, UserInfo o2) {
						if (o1.geteGrade() > o2.geteGrade()) {
							return 1;
						} else if (o1.geteGrade() < o2.geteGrade()) {
							return -1;
						} else {
							return 0;
						}
					}
				});
			} else if (str.equals("math")) {
				Collections.sort(users, new Comparator<UserInfo>() {
					@Override
					public int compare(UserInfo o1, UserInfo o2) {
						if (o1.getmGrade() > o2.getmGrade()) {
							return 1;
						} else if (o1.getmGrade() < o2.getmGrade()) {
							return -1;
						} else {
							return 0;
						}
					}
				});
			} else if (str.equals("print")) {
				Collections.sort(users, new Comparator<UserInfo>() {
					@Override
					public int compare(UserInfo o1, UserInfo o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
			}

			Iterator<UserInfo> itr = users.iterator();
			while (itr.hasNext()) {
				UserInfo val = itr.next();
				System.out.println(String.format("%s %d %d %d", val.getName(),
						val.getkGrade(), val.geteGrade(), val.getmGrade()));
			}
		}

	}

	private static List<UserInfo> getUsers(String fileNm) throws IOException {
		List<UserInfo> users = new ArrayList<UserInfo>();

		InputStream inputStream = new FileInputStream(fileNm);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(" ");
			UserInfo user = new UserInfo(tokens[0],
					Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),
					Integer.parseInt(tokens[3]));
			users.add(user);
		}

		return users;
	}
}
