package org.example.dmaker.exception;

public class DmakerException extends RuntimeException{
    private DmakerErrorCode dmakerErrorCode;
    private String detailMessage;


    public DmakerException(DmakerErrorCode dmakerErrorCode) {
        super(dmakerErrorCode.getDescription());
        this.dmakerErrorCode = dmakerErrorCode;
        this.detailMessage = dmakerErrorCode.getDescription();
    }

    public DmakerException(DmakerErrorCode dmakerErrorCode,String detailMessage) {
        super(detailMessage);
        this.dmakerErrorCode = dmakerErrorCode;
        this.detailMessage = detailMessage;
    }
}
