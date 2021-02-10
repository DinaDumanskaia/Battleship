

class Employee {

    String name;
    String email;
    int experience;

    Employee(String name, String email, int experience) {
        this.name = name;
        this.email = email;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getExperience() {
        return experience;
    }
}

class Developer extends Employee {

    String mainLanguage;
    String[] skills;

    Developer(String name, String email, int experience, String mainLanguage, String[] skills) {
        super(name, email, experience);
        this.mainLanguage = mainLanguage;
        this.skills = new String[skills.length];
        this.skills = copyArray(skills);

    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public String[] getSkills() {
        return copyArray(this.skills);
    }

    public static String[] copyArray(String[] source) {
        String[] copy = new String[source.length];
        System.arraycopy(source, 0, copy, 0, source.length);
        return copy;
    }
}

class DataAnalyst extends Employee {

    boolean phd;
    String[] methods;

    DataAnalyst(String name, String email, int experience, boolean phd, String[] methods) {
        super(name, email, experience);
        this.phd = phd;
        this.methods = Developer.copyArray(methods);
    }

    public boolean isPhd() {
        return phd;
    }

    public String[] getMethods() {
        return Developer.copyArray(this.methods);
    }
}