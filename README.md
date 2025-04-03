## **打开 IntelliJ IDEA**，进入 **设置**：

-  `IntelliJ IDEA` ➝ `Preferences` ➝ `Editor` ➝ `File and Code Templates`

- **选择 `Class` 模板，将下面内容全部替换**

```json
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

            /**
            * @file ${NAME}.java
			* @date ${DATE}
            * @function_description: 
			* @interface_description: 
			*     @calling_sequence: 
			*     @arguments_description: 
			*     @list_of_subordinate_classes: 
			* @discussion: 
			* @development_history: 
			*     @designer ${USER} 
			*     @reviewer: 
			*     @review_date: 
			*     @modification_date: 
			*     @description: 
            */
            
public class ${NAME} {
}

```

