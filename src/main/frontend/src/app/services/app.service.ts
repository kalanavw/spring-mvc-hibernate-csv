import {Injectable} from '@angular/core';
import {HttpService} from "../configuration/http.service";
import {PATH} from "../configuration/constants";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private httpService: HttpService) {

  }

  findAllCustomers() {
    let url = PATH.basePath + PATH.findAllCustomers;
    return this.httpService.httpGet(url);
  }

  findAllCustomersById(id: any) {
    let url = PATH.basePath + PATH.findAllCustomersById;
    return this.httpService.httpGet(url.replace('${id}', id));
  }

  findAllCustomersByName(name: any) {
    let url = PATH.basePath + PATH.findAllCustomersByName;
    return this.httpService.httpGet(url.replace('${name}', name));
  }

  findAllCustomersGroupByZipCode(zipCode: any) {
    let url = PATH.basePath + PATH.findAllCustomersGroupByZipCode;
    return this.httpService.httpGet(url.replace('${zipCode}', zipCode));
  }
}
