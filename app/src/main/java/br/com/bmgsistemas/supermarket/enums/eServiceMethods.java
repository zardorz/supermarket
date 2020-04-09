package br.com.bmgsistemas.supermarket.enums;

public enum eServiceMethods {
    //login
    USER_ACCOUNT_AUTHENTICATION,
//    USER_ACCOUNT_CREATE,
//    USER_ACCOUNT_LOGOUT,
//    USER_ACCOUNT_UPDATE,
//    USER_ACCOUNT_DELETE,

    //category
    CATEGORY_GETALL,
    CATEGORY_GETBYID,
    CATEGORY_GETBYPARENTID,
    CATEGORY_GETBYTYPE,
    CATEGORY_CREATE,
    CATEGORY_UPDATE,
    CATEGORY_DELETE,

    FINANCE_ACCOUNT,
    FINANCE_ACCOUNT_GETALL,
    FINANCE_ACCOUNT_GETBYID,
    FINANCE_ACCOUNT_GETBYTYPE,
    FINANCE_ACCOUNT_GETBYNAME,
    FINANCE_ACCOUNT_CREATE,
    FINANCE_ACCOUNT_UPDATE,
    FINANCE_ACCOUNT_DELETE,

    INSTITUITION_GETALL,
    INSTITUITION_GETBYID,
    INSTITUITION_CREATE,
    INSTITUITION_UPDATE,
    INSTITUITION_DELETE,

    STATEMENT_GETBYPERIOD,
    STATEMENT_GETBYID,
    STATEMENT_CREATE,
    STATEMENT_UPDATE,
    STATEMENT_DELETE,

    USER_GETALL,
    USER_GETBYID,
    USER_CREATE,
    USER_UPDATE,
    USER_DELETE,
}

/*

//category
 getAll() {
    return this.http.get<Response>(appConfig.secureApiUrl + '/categories')
  }

  getById(id: number) {
    return this.http.get<Response>(appConfig.secureApiUrl + '/categories/id/' + id);
  }

  getByParentId(parentId: number) {
    return this.http.get<Response>(appConfig.secureApiUrl + '/categories/parent/' + parentId);
  }

  getByType(typeID: number, parentID?: number) {
    return this.http.get<Response>(appConfig.secureApiUrl + '/categories/type/' + typeID + "/parent/" + parentID);
  }

  create(category: Category) {
    return this.http.post<Response>(appConfig.secureApiUrl + '/categories/', category);
  }

  update(category: Category) {
    return this.http.put<Response>(appConfig.secureApiUrl + '/categories/' + category.id, category);
  }

  delete(id: number) {
    return this.http.delete<Response>(appConfig.secureApiUrl + '/categories/' + id);


//finance_account
getAll() {
    //<Account[]>
    //debugger;
    return this.http.get<Response>(appConfig.secureApiUrl + '/FinanceAccounts/GetAll');
    //.map(res => {
    //  //debugger;
    //  if (res.Success) {
    //    return res.Result;
    //  } else {
    //    return [];
    //  }
    //});

    //return ret;
  }

  getById(id: string) {
    return this.http.get<Response>(appConfig.secureApiUrl + '/FinanceAccounts/id/' + id);
  }

  getByType(typeID: string) {
    return this.http.get<Response>(appConfig.secureApiUrl + '/FinanceAccounts/type/' + typeID);
  }

  getByName(name: string) {
    return this.http.get<Response>(appConfig.secureApiUrl + '/FinanceAccounts/name/' + name);
  }

  create(financeAccount: FinanceAccount) {
    return this.http.post<Response>(appConfig.secureApiUrl + '/FinanceAccounts/register', financeAccount);
  }

  update(financeAccount: FinanceAccount) {
    return this.http.put<Response>(appConfig.secureApiUrl + '/FinanceAccounts/' + financeAccount.id, financeAccount);
  }

  delete(id: string) {
    return this.http.delete<Response>(appConfig.secureApiUrl + '/FinanceAccounts/' + id);


//instituition
getAll() {
    //<Account[]>
    return this.http.get<Response>(appConfig.secureApiUrl + '/Instituitions')
    //.map(res => {
    //  //debugger;
    //  if (res.Success) {
    //    return res.Result;
    //  } else {
    //    return [];
    //  }
    //});

    //return ret;
  }

  getById(ID: string) {
    return this.http.get<Response>(appConfig.secureApiUrl + '/Instituitions/' + ID);
  }

  create(instituition: Instituition) {
    return this.http.post<Response>(appConfig.secureApiUrl + '/Instituitions/register', instituition);
  }

  update(instituition: Instituition) {
    return this.http.put<Response>(appConfig.secureApiUrl + '/Instituitions/' + instituition.ID, instituition);
  }

  delete(ID: string) {
    return this.http.delete<Response>(appConfig.secureApiUrl + '/Instituitions/' + ID);


//user
 getAll() {
    return this.http.get<Response>(appConfig.secureApiUrl + '/users');
    //  .map(res => {
    //    if (res.Success) {
    //      var users = <User[]>res.Result;
    //      return users;
    //    } else {
    //      return res.Errors;
    //    }
    //  });
    //return ret;
  }

  getById(_id: AAGUID) {
    return this.http.get(appConfig.secureApiUrl + '/users/' + _id);
  }

  create(register: Register) {
    return this.http.post<Response>(appConfig.secureApiUrl + '/users/register', register);
    //.map(res => {
    //  if (res.Success) {
    //    var users = <User[]>res.Result;
    //    return users;
    //  } else {
    //    return res.Errors;
    //  }
    //});
    //return ret;
  }

  update(user: User) {
    return this.http.put<Response>(appConfig.secureApiUrl + '/users/' + user.userID, user);
      //.map(res => {
    //  if (res.Success) {
    //    var users = <User[]>res.Result;
    //    return users;
    //  } else {
    //    return res.Errors;
    //  }
    //});
    //return ret;
  }

  delete(_id: AAGUID) {
    return this.http.delete<Response>(appConfig.secureApiUrl + '/users/' + _id);
      //.map(res => {
 */
