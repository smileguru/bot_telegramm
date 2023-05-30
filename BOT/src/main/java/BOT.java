import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BOT extends TelegramLongPollingBot {
    String vCom = "";
    boolean flagR = false;
    boolean autoriz = false;
    String name ;
    private long messageId;
    boolean record = false;

    @Override
    public String getBotUsername() {
        return "PersonalMentorForTraining_BOT";
    }

    @Override
    public String getBotToken() {
        return "6246439548:AAE4euIIesl1uIQ1awHzL0uuUB07wjG1g3o";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()&&update.getMessage().hasText()){
            var msg = update.getMessage();
            name = msg.getFrom().getFirstName();
            var users = msg.getFrom();
            var id = users.getId();
            if (msg.isCommand()){
                vCom = msg.getText();
                try {
                    switchCommand(id);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if ((flagR == true)&&(autoriz == false)){
                try {
                    switchLogin(id,msg.getText());
                } catch (TelegramApiException | SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if ((record)&&(autoriz)){
                try {
                    Person person = new Person(id,msg.getText());
                    sendText(id,"���� ������ ���� ���������");
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasCallbackQuery()){
            messageId = update.getCallbackQuery().getMessage().getMessageId();
            long cId = update.getCallbackQuery().getMessage().getChatId();
            vCom = update.getCallbackQuery().getData();
            try {
                switchCommand(cId);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void switchLogin(Long id,String str) throws TelegramApiException, SQLException, ClassNotFoundException {
        Login login = new Login();
        if(vCom.equals("/reg")){
            if (login.reg(id,str)){
                flagR = false;
                autoriz = true;
                iButton(id);
            }else{
                sendText(id, "������ �� ���������!!!\n��������� ������� ��� ���");
            }
        }else if(vCom.equals("/enter")){
            if (login.enter(id,str)){
                flagR = false;
                autoriz = true;
                iButton(id);
            }else{
                sendText(id, "������, �������� ������!\n������� ��� ��� ���");
            }
        }else if(vCom.equals("/enterID")){
            if (login.enterID(str)){
                flagR = false;
                autoriz = true;
                iButton(id);
            }else{
                sendText(id, "������, �������� id ��� ������!\n���������� ��� ���");
            }
        }
    }

    public void switchCommand (Long id) throws TelegramApiException {
        switch (vCom) {
            case "/start":
                sendText(id, "������������, � ������������ ������ ������!");
                iButton(id);
                break;
            case "/home":
                iButton(id);
                break;
            case "/reg":
                if (autoriz == true){
                    sendText(id, "�� ��� ���������� � ������� ������!");
                    flagR = false;
                }else {
                    sendText(id, "���������� � ������� ������ 2 ���� ����� ������:");
                    flagR = true;
                }
                break;
            case "/enter":
                if (autoriz == true){
                    sendText(id, "�� ��� ���������� � ������� ������!");
                    flagR = false;
                }else {
                    sendText(id, "������� ������");
                    flagR = true;
                }
                break;
            case "/enterID":
                if (autoriz == true){
                    sendText(id, "�� ��� ���������� � ������� ������!");
                    flagR = false;
                }else {
                    sendText(id, "������� id � ������ ����� ������");
                    flagR = true;
                }
                break;
            case "/rates":
                iButton(id);
                break;
            case "/create":
                iButton(id);
                break;
            case "/exit":
                autoriz = false;
                EditMessageText msg = new EditMessageText();
                msg.setChatId(id);
                msg.setText("�� ����� �� ����� ������� ������.\n�������� /start ��� ������.");
                msg.setMessageId((int) messageId);
                execute(msg);
                break;
            case "/personal":
                iButton(id);
                break;
            case "/back":
                iButton(id);
                break;
            case "/trener1":
                sendText(id,"���� ���������� ��������� �� ������:\nhttps://www.youtube.com/@Topstretching.workout");
                break;
            case "/trener2":
                sendText(id,"���� ���������� ��������� �� ������:\nhttps://www.youtube.com/@veryomina");
                break;
            case "/trener3":
                sendText(id,"���� ���������� ��������� �� ������:\nhttps://www.youtube.com/@ClubFitStars");
                break;
            case "/day1":
                sendText(id,"���������� 1:\n" +
                        "\n" +
                        "���� ������ ��� �������� � �������: 3*8-10;\n" +
                        "������������: 3*8-10;\n" +
                        "���� �������� ����� ���� ������� ������: 3*8-10;\n" +
                        "������ ������ �� ������ ����: 3*8-10;\n" +
                        "������ �������� ������: 3*8-10.");
                break;
            case "/day2":
                sendText(id,"���������� 2:\n" +
                        "\n" +"���������� �� ������� �� �����: 3*8-10;\n" +
                        "���� ������ � ������: 3*8-10;\n" +
                        "�������� ��� � ������: 3*8-10;\n" +
                        "���� ����� � ����� � �����: 3*8-10;\n" +
                        "������� �������� (����) ����� ������� �����: 3*10-12;\n" +
                        "������� �������� ����� ������� ����� � �������: 3*10-12.");
                break;
            case "/day3":
                sendText(id,"���������� 3:\n" +
                        "\n" +"���� ������ ���� �� �������������� ������: 3*8-10;\n" +
                        "���������� �������� ���� �� ��������� ������: 3*8-10;\n" +
                        "���������� �� ������� ������: 3*10-12;\n" +
                        "��� ������ ���� ����� ������: 3*8-10;\n" +
                        "���������� ��� �� ������� �����: 3*10-12.");
                break;
            case "/record":
                sendText(id,"�� ������ ������ ������ 5 �������\n����� ������� ������(���_54 ����_150)");
                record = true;
        }
    }

    public void botCommand(){
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand ("/home","������� ����"));
        botCommandList.add(new BotCommand ("/reg","�����������"));
        botCommandList.add(new BotCommand ("/enter","����"));
        botCommandList.add(new BotCommand ("/enterID","����� �� id"));
        botCommandList.add(new BotCommand ("/rates","������� �����"));
        botCommandList.add(new BotCommand ("/public","����� �����"));
        botCommandList.add(new BotCommand ("/personal","������������"));
        botCommandList.add(new BotCommand ("/create","��������� ����������"));
        botCommandList.add(new BotCommand ("/exit","�����"));
        botCommandList.add(new BotCommand ("/back","�����"));
        botCommandList.add(new BotCommand ("/trener1","�����1"));
        botCommandList.add(new BotCommand ("/trener2","�����2"));
        botCommandList.add(new BotCommand ("/trener3","�����3"));
        botCommandList.add(new BotCommand ("/day1","����������1"));
        botCommandList.add(new BotCommand ("/day2","����������2"));
        botCommandList.add(new BotCommand ("/day3","����������3"));
        botCommandList.add(new BotCommand ("/record","�������� ���. ������"));

        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(),null));
        }catch (TelegramApiException e){
            throw new RuntimeException(e);
        }
    }

    public void iButton (Long id) throws TelegramApiException {
        Button button = new Button(vCom);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(id.toString());
        if (vCom.equals("/start")) {
            sendMessage.setReplyMarkup(button.getMarkup());
            sendMessage.setText("�������� �����������/�����������:");
        }else if (vCom.equals("/home")){
            EditMessageText msg = new EditMessageText();
            msg.setChatId(id);
            msg.setText("������� ����");
            msg.setMessageId((int) messageId);
            msg.setReplyMarkup(button.getMarkup());
            execute(msg);
        }else if (vCom.equals("/reg")){
            sendMessage.setReplyMarkup(button.getMarkup());
            sendMessage.setText("�� ������� ���������������.\n��� id:"+id);
        }else if(vCom.equals("/enter")||vCom.equals("/enterID")){
            sendMessage.setReplyMarkup(button.getMarkup());
//            sendMessage.setText("����� ����������, "+name+"!");
            sendMessage.setText("����� ����������!");
        }else if(vCom.equals("/rates")){
            EditMessageText msg = new EditMessageText();
            msg.setChatId(id);
            msg.setText("�������� �����");
            msg.setMessageId((int) messageId);
            msg.setReplyMarkup(button.getMarkup());
            execute(msg);
        }else if (vCom.equals("/personal")){
            EditMessageText msg = new EditMessageText();
            msg.setChatId(id);
            msg.setText("�������� �����");
            msg.setMessageId((int) messageId);
            msg.setReplyMarkup(button.getMarkup());
            execute(msg);
        }else if(vCom.equals("/back")){
            EditMessageText msg = new EditMessageText();
            msg.setChatId(id);
            msg.setText("�������� �����");
            msg.setMessageId((int) messageId);
            msg.setReplyMarkup(button.getMarkup());
            execute(msg);
        }else if(vCom.equals("/create")){
            EditMessageText msg = new EditMessageText();
            msg.setChatId(id);
            msg.setText("�������� ����:");
            msg.setMessageId((int) messageId);
            msg.setReplyMarkup(button.getMarkup());
            execute(msg);
        }

        try {
            if (!(sendMessage.getText() == null)){
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder().chatId(who.toString()).text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
