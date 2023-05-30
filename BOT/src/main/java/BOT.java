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
                    sendText(id,"Ваши данные были сохранены");
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
                sendText(id, "Пароли не совпадают!!!\nПовторите попытку еще раз");
            }
        }else if(vCom.equals("/enter")){
            if (login.enter(id,str)){
                flagR = false;
                autoriz = true;
                iButton(id);
            }else{
                sendText(id, "Ошибка, неверный пароль!\nВведите его еще раз");
            }
        }else if(vCom.equals("/enterID")){
            if (login.enterID(str)){
                flagR = false;
                autoriz = true;
                iButton(id);
            }else{
                sendText(id, "Ошибка, неверный id или пароль!\nПопробуйте еще раз");
            }
        }
    }

    public void switchCommand (Long id) throws TelegramApiException {
        switch (vCom) {
            case "/start":
                sendText(id, "Здравствуйте, я персональный онлайн тренер!");
                iButton(id);
                break;
            case "/home":
                iButton(id);
                break;
            case "/reg":
                if (autoriz == true){
                    sendText(id, "Вы уже находитесь в учетной записи!");
                    flagR = false;
                }else {
                    sendText(id, "Придумайте и введите пароль 2 раза через пробел:");
                    flagR = true;
                }
                break;
            case "/enter":
                if (autoriz == true){
                    sendText(id, "Вы уже находитесь в учетной записи!");
                    flagR = false;
                }else {
                    sendText(id, "Введите пароль");
                    flagR = true;
                }
                break;
            case "/enterID":
                if (autoriz == true){
                    sendText(id, "Вы уже находитесь в учетной записи!");
                    flagR = false;
                }else {
                    sendText(id, "Введите id и пароль через пробел");
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
                msg.setText("Вы вышли из своей учетной записи.\nНапишите /start для начала.");
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
                sendText(id,"Ваша тренировка находится по ссылке:\nhttps://www.youtube.com/@Topstretching.workout");
                break;
            case "/trener2":
                sendText(id,"Ваша тренировка находится по ссылке:\nhttps://www.youtube.com/@veryomina");
                break;
            case "/trener3":
                sendText(id,"Ваша тренировка находится по ссылке:\nhttps://www.youtube.com/@ClubFitStars");
                break;
            case "/day1":
                sendText(id,"Тренировка 1:\n" +
                        "\n" +
                        "Тяга штанги или гантелей в наклоне: 3*8-10;\n" +
                        "Подтягивания: 3*8-10;\n" +
                        "Тяга верхнего блока вниз прямыми руками: 3*8-10;\n" +
                        "Подъем штанги на бицепс стоя: 3*8-10;\n" +
                        "Подъем гантелей «молот»: 3*8-10.");
                break;
            case "/day2":
                sendText(id,"Тренировка 2:\n" +
                        "\n" +"Приседания со штангой на спине: 3*8-10;\n" +
                        "Жимы ногами в станке: 3*8-10;\n" +
                        "Сгибания ног в станке: 3*8-10;\n" +
                        "Жимы вверх с груди в Смите: 3*8-10;\n" +
                        "Подъемы гантелей (махи) через стороны вверх: 3*10-12;\n" +
                        "Подъемы гантелей через стороны вверх в наклоне: 3*10-12.");
                break;
            case "/day3":
                sendText(id,"Тренировка 3:\n" +
                        "\n" +"Жимы штанги лежа на горизонтальной скамье: 3*8-10;\n" +
                        "Разведение гантелей лежа на наклонной скамье: 3*8-10;\n" +
                        "Кроссоверы на верхних блоках: 3*10-12;\n" +
                        "Жим штанги лежа узким хватом: 3*8-10;\n" +
                        "Разгибания рук на верхнем блоке: 3*10-12.");
                break;
            case "/record":
                sendText(id,"Вы можете внести только 5 записей\nЧерез пробелы пример(вес_54 рост_150)");
                record = true;
        }
    }

    public void botCommand(){
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand ("/home","главное меню"));
        botCommandList.add(new BotCommand ("/reg","регистрация"));
        botCommandList.add(new BotCommand ("/enter","вход"));
        botCommandList.add(new BotCommand ("/enterID","войти по id"));
        botCommandList.add(new BotCommand ("/rates","выбрать тариф"));
        botCommandList.add(new BotCommand ("/public","общий тариф"));
        botCommandList.add(new BotCommand ("/personal","персональный"));
        botCommandList.add(new BotCommand ("/create","составить тренировку"));
        botCommandList.add(new BotCommand ("/exit","выйти"));
        botCommandList.add(new BotCommand ("/back","назад"));
        botCommandList.add(new BotCommand ("/trener1","канал1"));
        botCommandList.add(new BotCommand ("/trener2","канал2"));
        botCommandList.add(new BotCommand ("/trener3","канал3"));
        botCommandList.add(new BotCommand ("/day1","тренировка1"));
        botCommandList.add(new BotCommand ("/day2","тренировка2"));
        botCommandList.add(new BotCommand ("/day3","тренировка3"));
        botCommandList.add(new BotCommand ("/record","записать физ. данные"));

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
            sendMessage.setText("Пройдите авторизацию/регистрацию:");
        }else if (vCom.equals("/home")){
            EditMessageText msg = new EditMessageText();
            msg.setChatId(id);
            msg.setText("Главное меню");
            msg.setMessageId((int) messageId);
            msg.setReplyMarkup(button.getMarkup());
            execute(msg);
        }else if (vCom.equals("/reg")){
            sendMessage.setReplyMarkup(button.getMarkup());
            sendMessage.setText("Вы успешно зарегистированы.\nВаш id:"+id);
        }else if(vCom.equals("/enter")||vCom.equals("/enterID")){
            sendMessage.setReplyMarkup(button.getMarkup());
//            sendMessage.setText("Добро пожаловать, "+name+"!");
            sendMessage.setText("Добро пожаловать!");
        }else if(vCom.equals("/rates")){
            EditMessageText msg = new EditMessageText();
            msg.setChatId(id);
            msg.setText("Выберите тариф");
            msg.setMessageId((int) messageId);
            msg.setReplyMarkup(button.getMarkup());
            execute(msg);
        }else if (vCom.equals("/personal")){
            EditMessageText msg = new EditMessageText();
            msg.setChatId(id);
            msg.setText("Выберите канал");
            msg.setMessageId((int) messageId);
            msg.setReplyMarkup(button.getMarkup());
            execute(msg);
        }else if(vCom.equals("/back")){
            EditMessageText msg = new EditMessageText();
            msg.setChatId(id);
            msg.setText("Выберите тариф");
            msg.setMessageId((int) messageId);
            msg.setReplyMarkup(button.getMarkup());
            execute(msg);
        }else if(vCom.equals("/create")){
            EditMessageText msg = new EditMessageText();
            msg.setChatId(id);
            msg.setText("Выберите день:");
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
