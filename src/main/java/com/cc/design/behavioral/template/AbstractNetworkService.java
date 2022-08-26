package com.cc.design.behavioral.template;

/**
 * 因为一个个请求经过的流程为一下步骤：
 *

 */
public abstract class AbstractNetworkService {
    String userName;
    String password;

    public AbstractNetworkService(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    abstract boolean logIn(String userName, String password);
    void claimHand(){
        System.out.println("claim:  ready to hand check !!!");
    }
    abstract boolean sendData(byte[] data);
    abstract void logOut();

    /**
     * Publish the data to whatever network.
     */
    public boolean post(String message) {
        // Authenticate before posting. Every network uses a different
        // authentication method.
        if (logIn(this.userName, this.password)) {
            // 这里是一个固定动作
            claimHand();
            // Send the post data.
            boolean result =  sendData(message.getBytes());
            logOut();
            return result;
        }
        return false;
    }

}
