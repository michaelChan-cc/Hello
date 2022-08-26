package com.cc.design.behavioral.template;

public class Facebook extends AbstractNetworkService{

    public Facebook(String userName, String password) {
        super(userName, password);
    }

    @Override
    boolean logIn(String userName, String password) {
        System.out.println("\n\nLogIn success on Facebook");
        return true;
    }

    @Override
    boolean sendData(byte[] data) {
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on Facebook");
            return true;
        } else {
            return false;
        }
    }

    @Override
    void logOut() {
        System.out.println("User: '" + userName + "' was logged out from Facebook");
    }

    @Override
    void claimHand() {
        System.out.println("这里是facebook， 我要重写一下claim");
    }
}
