//
//  Networking.m
//  Google_News
//
//  Created by Hao Zhou on 2/15/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "SharedNetworking.h"

@implementation SharedNetworking
+ (id)sharedNetworking
{
    static dispatch_once_t pred;
    static SharedNetworking *shared = nil;
    
    dispatch_once(&pred, ^{
        shared = [[self alloc] init];
    });
    return shared;
}

- (id) init
{
    if (self = [super init]) {
        
    }
    return self;
}


- (void) getFeedForURL:(NSString*)url
                       success:(void (^)(NSMutableDictionary *dictionary, NSError *error))successCompletion
                       failure:(void (^)(void))failureCompletion
{
    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
    
    [[[NSURLSession sharedSession] dataTaskWithURL:[NSURL URLWithString:url]
                                 completionHandler:^(NSData *data,
                                                     NSURLResponse *response,
                                                     NSError *error) {
                                     

                                     [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
                                     


                                     
                                     NSHTTPURLResponse *httpResp = (NSHTTPURLResponse*) response;
                                     if (httpResp.statusCode == 200) {
                                         NSError *jsonError;
                                         
                                         NSMutableDictionary *dictionary = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingAllowFragments error:&jsonError];

                                         successCompletion(dictionary,nil);
                                     } else {
                                         NSLog(@"Fail Not 200:");
                                         UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Alert!" message:@"No network available. Please make sure your device is not on airplane mode." delegate:self cancelButtonTitle:@"ok" otherButtonTitles:nil];
                                         [alert show];
                                         dispatch_async(dispatch_get_main_queue(), ^{
                                             if (failureCompletion) failureCompletion();
                                         });
                                     }
                                 }] resume];
}

- (bool) networkIsActive {
    if ([UIApplication sharedApplication].networkActivityIndicatorVisible == YES) {
        return true;
    }
    else {
        return false;
    }
}

@end
