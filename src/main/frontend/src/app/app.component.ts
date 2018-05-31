import {Component, OnInit} from '@angular/core';
import {AppService} from "./services/app.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  customers: any
  id: any;
  name: any;
  zipCode: any;

  constructor(private appService: AppService) {

  }

  ngOnInit(): void {
    this.appService.findAllCustomers()
      .subscribe(res => {
        console.log(res);
        this.customers = res;
      });
  }

  public searchCustomersByID() {
    this.appService.findAllCustomersById(this.id)
      .subscribe(res => {
        console.log(res);
        this.customers = res;
      });
  }

  public searchCustomersByName() {
    this.appService.findAllCustomersByName(this.name)
      .subscribe(res => {
        console.log(res);
        this.customers = res;
      });
  }

  public searchCustomersByZipCode() {
    this.appService.findAllCustomersGroupByZipCode(this.zipCode)
      .subscribe(res => {
        console.log(res);
        this.customers = res;
      });
  }
}
