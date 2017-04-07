package myServlet;

public class ViewModel {

    private String view;

    public ViewModel() {
    }

    public ViewModel(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
