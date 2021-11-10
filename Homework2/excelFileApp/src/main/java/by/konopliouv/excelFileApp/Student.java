package by.konopliouv.excelFileApp;

public class Student {

	private String firstName;
	private String secondName;
	private int age;
	private String groupName;
	private int studentID;
	
	public Student() {
		
	}
	
	public Student(String firstName,String secondName,int age,
			String groupName,int studentID) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.age = age;
		this.groupName = groupName;
		this.studentID = studentID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + studentID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		if (studentID != other.studentID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", secondName=" + secondName + ", age=" + age + ", groupName="
				+ groupName + ", studentID=" + studentID + "]";
	}
	
	
}
