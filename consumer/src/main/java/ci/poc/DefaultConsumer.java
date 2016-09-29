package ci.poc;

/**
 * Date of creation: 29.09.2016
 * <p>
 * Copyright \(c\) CompuGROUP Software GmbH,
 * This software is the confidential and proprietary information of
 * CompuGROUP Software GmbH. You shall not disclose such confidential
 * information and shall use it only in accordance with the terms of
 * the license agreement you entered into with CompuGROUP Software GmbH.
 */
public class DefaultConsumer {
    final private G3MicroService service = new G3MicroServiceImpl();
    public int usedCount() {
        return service.getCount();
    }
}
