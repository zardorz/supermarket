package br.com.bmgsistemas.supermarket.interfaces;

public interface OnCustomEventListener {
    void onEvent();
    void onEventPosition(int value);
    void onEventValue(int value);
    void onEventAll(int value, int position);
}
