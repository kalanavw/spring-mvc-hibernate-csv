import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {catchError, tap} from 'rxjs/operators';
import {Observable} from "rxjs/internal/Observable";
import {of} from "rxjs/internal/observable/of";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class HttpService {

  constructor(private http: HttpClient) {
  }

  httpGet(url: any) {
    return this.http.get(url)
      .pipe(
        tap(res => this.log(`fetched res`)),
        catchError(this.handleError('res'))
      );
  }

  httpPost(url: any, request: any) {
    return this.http.post(url, request, httpOptions).pipe(
      tap(res => this.log(`fetched res`)),
      catchError(this.handleError('res'))
    );
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log(message);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
