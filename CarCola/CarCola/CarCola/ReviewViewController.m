//
//  ReviewViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/13/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "ReviewViewController.h"

@interface ReviewViewController ()

@end

@implementation ReviewViewController

- (void)downloadData {

    
    NSUserDefaults *getCar = [NSUserDefaults standardUserDefaults];
    NSMutableArray *getCarArr = [getCar objectForKey:@"toGallery"];
    
    NSArray *strArr = [[getCarArr objectAtIndex:1] componentsSeparatedByString:@","];
    
    NSString *make = [getCarArr objectAtIndex:0];
    NSString *model = [strArr objectAtIndex:0];
    
    //NSString *secondString = [s stringByReplacingOccurrencesOfString:@" " withString:@""];
    //remove potential space user entered in the text.
    NSString *submodel = [[strArr objectAtIndex:1] stringByReplacingOccurrencesOfString:@" " withString:@""];
    NSString *trim = [getCarArr objectAtIndex:2];
    NSString *year = [getCarArr objectAtIndex:3];
    
    NSLog(@"the transfered car is %@ %@ %@ %@ %@ in datapack %@", make, model, submodel, trim, year, [getCar objectForKey:@"toGallery"]);
    
    //Reconstruct the url to fetch api Jason.
    NSString* url = [NSString stringWithFormat: @"https://api.edmunds.com/api/vehicle/v2/grade/%@/%@/%@?submodel=%@&fmt=json&api_key=fymj9cve2x2vjwxsynhk43w8", make, model, year, submodel];
    
    [[SharedNetworking sharedNetworking] getFeedForURL:url
                                               success:^(NSMutableDictionary *dictionary, NSError *error) {
                                                   
                                                   
                                                   NSLog(@"the method inside dictionary is %@", dictionary);
                                                   
                                                   // Use dispatch_async to update the table on the main thread
                                                   dispatch_async(dispatch_get_main_queue(), ^{
                                                       
                                               
//                                                       [self.tableView reloadData];
                                                       //                                                       [self.delegate removeSplash:self sendObject:true];
                                                       
                                                   });
                                               }
                                               failure:^{
                                                   dispatch_async(dispatch_get_main_queue(), ^{
                                                       NSLog(@"Problem with Data");
                                                   });
                                               }];


}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.

//    [self downloadData];
    NSUserDefaults *getCar = [NSUserDefaults standardUserDefaults];
    NSMutableArray *getCarArr = [getCar objectForKey:@"toGallery"];
    
    NSArray *strArr = [[getCarArr objectAtIndex:1] componentsSeparatedByString:@","];
    
    NSString *make = [getCarArr objectAtIndex:0];
    NSString *model = [strArr objectAtIndex:0];
    
    //NSString *secondString = [s stringByReplacingOccurrencesOfString:@" " withString:@""];
    //remove potential space user entered in the text.
    NSString *submodel = [[strArr objectAtIndex:1] stringByReplacingOccurrencesOfString:@" " withString:@""];
    NSString *trim = [getCarArr objectAtIndex:2];
    NSString *year = [getCarArr objectAtIndex:3];
    
    NSLog(@"the transfered car is %@ %@ %@ %@ %@ in datapack %@", make, model, submodel, trim, year, [getCar objectForKey:@"toGallery"]);

    
    //Reconstruct the url to fetch api Jason.
    NSString* url = [NSString stringWithFormat: @"https://api.edmunds.com/api/vehicle/v2/grade/%@/%@/%@?submodel=%@&fmt=json&api_key=fymj9cve2x2vjwxsynhk43w8", make, model, year, submodel];
    
    [[SharedNetworking sharedNetworking] getFeedForURL:url
                                               success:^(NSMutableDictionary *dictionary, NSError *error) {
 

                                                   [self makeReview:dictionary];
                                                   
                                                   [self storeStyleID:[dictionary[@"style"] objectForKey:@"id"]];
                                                   // Use dispatch_async to update the table on the main thread
                                                   dispatch_async(dispatch_get_main_queue(), ^{
                                                       
                                                       NSLog(@"Network connection succeed.");
                                                       
                                                       [self printOut];
 
                                                       //                                                       [self.delegate removeSplash:self sendObject:true];
                                                       
                                                   });
                                               }
                                               failure:^{
                                                   dispatch_async(dispatch_get_main_queue(), ^{
                                                       NSLog(@"Problem with Data");
                                                   });
                                               }];
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(downloadData)
                                                 name:NSUserDefaultsDidChangeNotification
                                               object:nil];

    
    
}

-(void)makeReview:(NSMutableDictionary *)dict {

    _grade = [dict objectForKey:@"grade"];
    _summary = [dict objectForKey:@"summary"];

    //handle the sub-dictionaries
    NSMutableArray *subArr = [[NSMutableArray alloc] init];
    for (NSDictionary *subDict in dict[@"ratings"]){
        [subArr addObject:subDict];
    }
    
    
    
    //take care of the five sub-dictionary categories under dictionary of ratings. There are more sub-sub-categories which are not handled here.
    _performance = [[subArr objectAtIndex:0] objectForKey:@"grade"];
    _comfort = [[subArr objectAtIndex:1] objectForKey:@"grade"];
    _interior = [[subArr objectAtIndex:2] objectForKey:@"grade"];
    _value = [[subArr objectAtIndex:3] objectForKey:@"grade"];
    _funtodrive = [[subArr objectAtIndex:4] objectForKey:@"grade"];

//    NSLog(@"is dict null %@", dict == nil?@"YES":@"NO");
//    NSLog(@"the dict has %@", dict);
    

}

-(void)printOut {
    self.GradeLabel.text = [NSString stringWithFormat:@"Grade: %@", _grade];
    self.SummaryLabel.text = [NSString stringWithFormat:@"Summary: %@", _summary];
    self.PerformanceLabel.text = [NSString stringWithFormat:@"Performance Grade: %@", _performance];
    self.ComfortLabel.text = [NSString stringWithFormat:@"Comfort Grade: %@", _comfort];
    self.InteriorLabel.text = [NSString stringWithFormat:@"Interior Grade: %@", _interior];
    self.ValueLabel.text = [NSString stringWithFormat:@"Value Grade: %@", _value];
    self.FunToDriveLabel.text = [NSString stringWithFormat:@"Drive Grade: %@", _funtodrive];


    
//    NSLog(@"self grade and summary are %@ and %@", _GradeLabel, _PerformanceLabel);
}

-(void)storeStyleID:(NSString *) styleID {
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    [user setObject:styleID forKey:@"idForImage"];
    [user synchronize];
    
    NSLog(@"the image ID is %@", [user objectForKey:@"idForImage"]);
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (IBAction)backButton:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];

}
@end
