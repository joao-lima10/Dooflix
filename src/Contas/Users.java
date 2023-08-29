package Contas;

public class Users {

    public String name;
    public String cpf;
    public String email;
    public String username;
    public String password;

    public Users(String name, String cpf, String email, String username, String password) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsarname(String usarname) {
        this.username = usarname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString(){

        return "\nName: " + getName() +
                "\nCpf: " + getCpf() +
                "\nEmail: " + getCpf() +
                "\nUsername: " + getUsername();

    }
}
