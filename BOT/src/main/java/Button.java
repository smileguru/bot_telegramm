import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Button {
    private List <List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
    private List <InlineKeyboardButton> rowInline1 = new ArrayList<>();
    private List <InlineKeyboardButton> rowInline2 = new ArrayList<>();
    private List <InlineKeyboardButton> rowInline3 = new ArrayList<>();
    private List <InlineKeyboardButton> rowInline4 = new ArrayList<>();
    private List <InlineKeyboardButton> rowInline5 = new ArrayList<>();
    private String vCom;
    private InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

    public Button(String vCom) {
        this.vCom = vCom;
        switch (vCom) {
            case "/start":
                loginB();
                break;
            case "/home":
                menuB();
                break;
            case "/reg":
                menuB();
                break;
            case "/enter":
                menuB();
                break;
            case "/enterID":
                menuB();
                break;
            case "/rates":
                ratesB();
                break;
            case "/personal":
                personalRates();
                break;
            case "/back":
                ratesB();
                break;
            case "/create":
                createB();
                break;
        }
    }

    private void createB(){ //�������� ����������
        var button1 = new InlineKeyboardButton();
        button1.setText("���������� 1");
        button1.setCallbackData("/day1");
        rowInline1.add(button1);

        var button2 = new InlineKeyboardButton();
        button2.setText("���������� 2");
        button2.setCallbackData("/day2");
        rowInline2.add(button2);

        var button3 = new InlineKeyboardButton();
        button3.setText("���������� 3");
        button3.setCallbackData("/day3");
        rowInline3.add(button3);

        var button4 = new InlineKeyboardButton();
        button4.setText("�����");
        button4.setCallbackData("/home");
        rowInline4.add(button4);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline4);
        markup.setKeyboard(rowsInline);
    }

    private void personalRates(){ //������������ �����
        var button1 = new InlineKeyboardButton();
        button1.setText("Workout � ���� � �����");
        button1.setCallbackData("/trener1");
        rowInline1.add(button1);

        var button2 = new InlineKeyboardButton();
        button2.setText("���ܨ���� � �������� � ������");
        button2.setCallbackData("/trener2");
        rowInline2.add(button2);

        var button3 = new InlineKeyboardButton();
        button3.setText("FITSTARS (Yougifted) � ������ ������ ����");
        button3.setCallbackData("/trener3");
        rowInline3.add(button3);

        var button4 = new InlineKeyboardButton();
        button4.setText("�����");
        button4.setCallbackData("/back");
        rowInline4.add(button4);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline4);
        markup.setKeyboard(rowsInline);
    }

    private void loginB (){ // ����������� / ���� / ���� �� ������
        var button1 = new InlineKeyboardButton();
        button1.setText("�����������");
        button1.setCallbackData("/reg");
        rowInline1.add(button1);

        var button2 = new InlineKeyboardButton();
        button2.setText("����");
        button2.setCallbackData("/enter");
        rowInline2.add(button2);

        var button3 = new InlineKeyboardButton();
        button3.setText("���� �� id");
        button3.setCallbackData("/enterID");
        rowInline2.add(button3);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        markup.setKeyboard(rowsInline);
    }

    private void menuB (){ // ������� ����
        var button1 = new InlineKeyboardButton();
        button1.setText("������� �����");
        button1.setCallbackData("/rates");
        rowInline1.add(button1);

        var button2 = new InlineKeyboardButton();
        button2.setText("��������� ����������");
        button2.setCallbackData("/create");
        rowInline2.add(button2);

        var button3 = new InlineKeyboardButton();
        button3.setText("�������� ���. ������");
        button3.setCallbackData("/record");
        rowInline3.add(button3);

        var button5 = new InlineKeyboardButton();
        button5.setText("�����");
        button5.setCallbackData("/exit");
        rowInline5.add(button5);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline5);
        markup.setKeyboard(rowsInline);
    }

    private void ratesB (){ // ������
        var button1 = new InlineKeyboardButton();
        button1.setText("�����");
        button1.setCallbackData("/public");
        rowInline1.add(button1);

        var button2 = new InlineKeyboardButton();
        button2.setText("������������");
        button2.setCallbackData("/personal");
        rowInline1.add(button2);

        var button3 = new InlineKeyboardButton();
        button3.setText("������� ����");
        button3.setCallbackData("/home");
        rowInline2.add(button3);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        markup.setKeyboard(rowsInline);
    }

    public InlineKeyboardMarkup getMarkup() {
        return markup;
    }
}
